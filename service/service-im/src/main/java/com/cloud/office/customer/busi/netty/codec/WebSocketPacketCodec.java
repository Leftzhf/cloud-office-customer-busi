package com.cloud.office.customer.busi.netty.codec;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * WebSocket数据包编解码器
 *
 *
 */
@ChannelHandler.Sharable
@Component
public class WebSocketPacketCodec extends MessageToMessageCodec<WebSocketFrame, Packet> {

    @Autowired
    private PacketCodeC packetCodeC;

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        packetCodeC.encode(byteBuf, msg, ctx.channel());
        out.add(new BinaryWebSocketFrame(byteBuf));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        out.add(packetCodeC.decode(msg.content()));
    }

}
