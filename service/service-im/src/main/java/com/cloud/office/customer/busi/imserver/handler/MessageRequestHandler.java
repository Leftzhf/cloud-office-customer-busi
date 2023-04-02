package com.cloud.office.customer.busi.imserver.handler;

import com.cloud.office.customer.busi.imserver.protocol.MessageProtoBuf;
import com.cloud.office.customer.busi.imserver.session.Session;
import com.cloud.office.customer.busi.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageProtoBuf.ImMessage> {

    private Logger log = LoggerFactory.getLogger(MessageRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtoBuf.ImMessage imMessage) throws Exception {
        // 拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        log.info("收到来自{}的消息:{}", imMessage.getSendMessageRequest().getSenderId(), imMessage.getSendMessageRequest().getContent().toString(StandardCharsets.UTF_8));


        //TODO 把消息内容存入MongoDB

//        // 拿到消息接收方的 channel
//        Channel toUserChannel = SessionUtil.getChannel(ImMessage.getSendMessageRequest().getReceiverId());
//
//        // 将消息转发给消息接收方
//        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
//            toUserChannel.writeAndFlush(ImMessage).addListener(future -> {
//                if (future.isSuccess()) {
//                    //发送方返回消息
//                    MessageProtoBuf.ImMessage imMessageResponse = MessageProtoBuf.ImMessage.newBuilder().setSendMessageResponse(MessageProtoBuf.SendMessageResponse
//                            .newBuilder()
//                            .setStatus(MessageProtoBuf.SendMessageResponse.Status.SUCCESS)
//                            .setMessage("发送成功")
//                            .build()).build();
//                    channelHandlerContext.writeAndFlush(imMessageResponse);
//                }else {
//                    //发送方返回消息
//                    MessageProtoBuf.ImMessage imMessageResponse = MessageProtoBuf.ImMessage.newBuilder()
//                            //设置头部字段
//                            .setHeader(MessageProtoBuf.Header.newBuilder()
//                                    .setMagicNumber(0x12345678)
//                                    .setMessageType(MessageProtoBuf.Header.MessageType.SEND_MESSAGE_RESPONSE_VALUE)
//                                    .build())
//                            //设置消息体字段
//                            .setSendMessageResponse(MessageProtoBuf.SendMessageResponse.newBuilder()
//                                .setStatus(MessageProtoBuf.SendMessageResponse.Status.FAILURE)
//                                .setMessage("发送失败")
//                                .build())
//                            .build();
//                    //写回给发送方
//                    channelHandlerContext.writeAndFlush(imMessageResponse);
//                }
//            });
//        } else {
//            System.err.println("[" + ImMessage.getSendMessageRequest().getReceiverId() + "] 不在线，发送失败!");
//        }
    }
}
