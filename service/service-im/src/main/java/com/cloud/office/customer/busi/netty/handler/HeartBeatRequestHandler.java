package com.cloud.office.customer.busi.netty.handler;

import com.cloud.office.customer.busi.netty.protocol.request.HeartBeatRequestPacket;
import com.cloud.office.customer.busi.netty.protocol.response.HeartBeatResponsePacket;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 心跳检测,每5s一次
 *
 *
 */
@ChannelHandler.Sharable
@Component
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
