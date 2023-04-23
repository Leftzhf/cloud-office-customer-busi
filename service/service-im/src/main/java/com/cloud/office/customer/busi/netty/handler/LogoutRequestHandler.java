package com.cloud.office.customer.busi.netty.handler;

import com.cloud.office.customer.busi.netty.protocol.request.LogoutRequestPacket;
import com.cloud.office.customer.busi.netty.protocol.response.LogoutResponsePacket;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

/**
 */
@ChannelHandler.Sharable
@Component
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        ChannelUtil.unBindUser(ctx.channel());

        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
