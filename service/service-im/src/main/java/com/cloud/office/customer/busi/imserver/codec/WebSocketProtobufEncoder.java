package com.cloud.office.customer.busi.imserver.codec;

import com.cloud.office.customer.busi.imserver.protocol.MessageProto;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/31 09:07
 */
public class WebSocketProtobufEncoder extends MessageToMessageEncoder<MessageLiteOrBuilder> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageLiteOrBuilder messageLiteOrBuilder, List<Object> out) throws Exception {
        ByteBuf byteBuf= null;
        if (messageLiteOrBuilder instanceof MessageLite) {
            byteBuf = Unpooled.wrappedBuffer(((MessageLite) messageLiteOrBuilder).toByteArray());
        } else {
            if (messageLiteOrBuilder instanceof MessageLite.Builder) {
                byteBuf = Unpooled.wrappedBuffer(((MessageLite.Builder) messageLiteOrBuilder).build().toByteArray());
            }
        }
        WebSocketFrame frame = new BinaryWebSocketFrame(byteBuf);
        out.add(frame);
    }
}

