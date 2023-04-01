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
//        log.info("打印消息：{}",((TextWebSocketFrame)frame).text());
        ByteBuf buf = frame.content();
        //魔数判断，如果不符合则拒绝连接
        int magincNumber = buf.readInt();
//        if (magincNumber != 0x12345678) {
//            ctx.channel().close();
//            return;
//        }
        out.add(buf);
        buf.retain();
    }
}

