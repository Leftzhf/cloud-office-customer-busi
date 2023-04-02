package com.cloud.office.customer.busi.imserver.codec;

import cn.hutool.log.Log;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/31 09:26
 */
public class WebSocketProtobufDecoder extends MessageToMessageDecoder<WebSocketFrame> {

    private static final Logger log = LoggerFactory.getLogger(WebSocketProtobufDecoder.class);
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> out) throws Exception {
        ByteBuf buf = frame.content();
        buf.retain();
        out.add(buf);
    }
}

