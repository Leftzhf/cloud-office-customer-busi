package com.cloud.office.customer.busi.imserver.handler;

import com.cloud.office.customer.busi.imserver.protocol.MessageProtoBuf;
import com.cloud.office.customer.busi.imserver.session.Session;
import com.cloud.office.customer.busi.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<MessageProtoBuf.ImMessage> {
    //日志记录器
    private static final Logger log = LoggerFactory.getLogger(LoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtoBuf.ImMessage imMessage) {
        //访客登录，直接分配一个userId
        String userId = randomUserId();
        log.info("访客{}登录成功", userId);
        ctx.fireChannelRead(imMessage);
        //加入session
//        SessionUtil.bindSession(new Session(userId, imMessage.getUserName()), ctx.channel());
    }



    private boolean valid() {
        //TODO 登录校验采用token jwt,从网关转发携带token，这里校验token
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
