package com.cloud.office.customer.busi.netty.handler;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.netty.protocol.request.MessageRequestPacket;
import com.cloud.office.customer.busi.netty.protocol.response.MessageResponsePacket;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.entity.Message;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.utils.RestTemplateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息转发请求逻辑处理器
 *
 *
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ServiceUsercenterClient userService;
  @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {

        // 消息发送方
        User fromUser = ChannelUtil.getUser(ctx.channel());

        // 将消息存入数据库
        Message message = new Message();
        message.setType(msg.getType());
        message.setContent(msg.getContent());
        message.setFromUserId(fromUser.getId());
        message.setToUserId(msg.getToUserId());
        //todo 后续改成mongo
        boolean result = messageService.save(message);
        if (!result) {
            log.info("消息存入数据库失败,message={}", JSON.toJSONString(message));
            return;
        }

        // 消息响应数据包
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setId(message.getId());
        messageResponsePacket.setType(message.getType());
        messageResponsePacket.setContent(message.getContent());
        messageResponsePacket.setFromUserId(message.getFromUserId());
        messageResponsePacket.setToUserId(message.getToUserId());
        messageResponsePacket.setStatus(message.getStatus());
        messageResponsePacket.setCreatedAt(message.getCreatedAt());
        messageResponsePacket.setUpdatedAt(message.getUpdatedAt());

        // 发送给消息发送方，用于判断消息是否发送成功
        ctx.channel().writeAndFlush(messageResponsePacket);

        // 消息接收方 todo 改成rest接口请求
        User toUser = restTemplateUtil.getById(msg.getToUserId());
//        User toUser = userService.getById(msg.getToUserId());
        if (toUser == null) {
            log.info("userId={}用户不存在，消息发送失败!", msg.getToUserId());
            return;
        }

        // 消息接收方的Channel
        Channel toChannel = ChannelUtil.getChannel(toUser.getUsername());
        if (toChannel != null && ChannelUtil.hasLogin(toChannel)) {
            // 发送给消息接收方
            toChannel.writeAndFlush(messageResponsePacket);
        } else {
            log.info("username={}不在线!", toUser.getUsername());
        }

    }
}
