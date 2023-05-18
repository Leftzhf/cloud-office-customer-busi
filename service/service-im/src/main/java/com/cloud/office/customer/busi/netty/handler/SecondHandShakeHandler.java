package com.cloud.office.customer.busi.netty.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.office.customer.busi.netty.attribute.Attributes;
import com.cloud.office.customer.busi.netty.protocol.request.SecondHandShakeRequestPacket;
import com.cloud.office.customer.busi.netty.protocol.response.SecondHandShakeResponsePacket;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import com.cloud.office.customer.busi.netty.utils.SessionManager;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_im.entity.Message;
import com.cloud.office.customer.busi.service_im.entity.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZuoHaoFan
 * @Description: 第二次握手处理器:交换密钥
 * @date 2023/5/10 20:26
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class SecondHandShakeHandler extends SimpleChannelInboundHandler<SecondHandShakeRequestPacket> {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ConversationService conversationService;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SecondHandShakeRequestPacket secondHandShakeRequestPacket) throws Exception {
        log.info("第二次握手: 会话id{}",secondHandShakeRequestPacket.getSessionId());
        Integer visitorId = secondHandShakeRequestPacket.getVisitorId();
        Integer serverId = secondHandShakeRequestPacket.getServerId();
        String visitorKey = ChannelUtil.getSecretKey(visitorId);
        String serverKey = ChannelUtil.getSecretKey(serverId);
        Integer sessionId = secondHandShakeRequestPacket.getSessionId();

        SessionManager sessionManager = SessionManager.getInstance();
        //如果session不存在则创建session
        if (!sessionManager.hasSession(sessionId)){
            sessionManager.createSession(sessionId, visitorId, visitorKey, serverId, serverKey, channelHandlerContext.channel());
        }
        Channel channel = channelHandlerContext.channel();
        if (channel.attr(Attributes.CONVERSATION_ATTRIBUTE_KEY).get() == null){
            Map<Integer, Session> conversationMap = new HashMap<>();
            conversationMap.put(sessionId, sessionManager.getSession(sessionId));
            channel.attr(Attributes.CONVERSATION_ATTRIBUTE_KEY).set(conversationMap);
        }else {
            //如果已存在则更新
            channel.attr(Attributes.CONVERSATION_ATTRIBUTE_KEY).get().putIfAbsent(sessionId, sessionManager.getSession(sessionId));
        }
        //通知对方交换密钥
        SecondHandShakeResponsePacket secondHandShakeResponsePacket = new SecondHandShakeResponsePacket();
        secondHandShakeResponsePacket.setServerKey(serverKey);
        secondHandShakeResponsePacket.setVisitorKey(visitorKey);
        //如果当前的channel是访客，就给客服发，如果是客服就给访客发
        if (ChannelUtil.getId(channelHandlerContext.channel()).equals(visitorId)){
            ChannelUtil.getChannel(serverId).writeAndFlush(secondHandShakeResponsePacket);
        }else {
            ChannelUtil.getChannel(visitorId).writeAndFlush(secondHandShakeResponsePacket);
        }
        channelHandlerContext.channel().writeAndFlush(secondHandShakeResponsePacket);

    }

    /**
     * 连接断开回调
     *
     * @param ctx ctx
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionManager sessionManager = SessionManager.getInstance();
        List<Integer> conversationIdList = ChannelUtil.getConversationId(ctx.channel());
        //移除会话信息
        sessionManager.removeSession(ChannelUtil.getConversationId(ctx.channel()), ctx.channel());
        //todo 通知后台下线
        log.info("会话断开！移除会话Session信息！");
        //关闭会话
        conversationIdList.forEach(conversationId->{
            ChannelUtil.unBindUser(ctx.channel());
            //更新最后一条消息id
            QueryWrapper<Message> wrapper = new QueryWrapper<>();
            wrapper.select("max(id) as id");
            wrapper.eq("conversation_id", conversationId);
            Integer lastMessageId = messageService.getOne(wrapper).getId();
            Conversation conversation = new Conversation();
            conversation.setId(conversationId);
            conversation.setLastMessageId(lastMessageId);
            conversationService.updateById(conversation);
        });
    }
}
