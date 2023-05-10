package com.cloud.office.customer.busi.netty.codec;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@ChannelHandler.Sharable
@Component
public class TcpPacketCodec extends MessageToMessageCodec<ByteBuf, Packet> {

    @Autowired
    private PacketCodeC packetCodeC;

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        packetCodeC.encode(byteBuf, msg, ctx.channel());
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(packetCodeC.decode(msg));
    }
}
