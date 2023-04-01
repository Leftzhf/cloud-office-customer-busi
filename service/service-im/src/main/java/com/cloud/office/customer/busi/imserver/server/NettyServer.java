package com.cloud.office.customer.busi.imserver.server;

import com.cloud.office.customer.busi.imserver.codec.WebSocketProtobufDecoder;
import com.cloud.office.customer.busi.imserver.codec.WebSocketProtobufEncoder;
import com.cloud.office.customer.busi.imserver.handler.MessageRequestHandler;
import com.cloud.office.customer.busi.imserver.protocol.MessageProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrameEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/29 19:53
 */
@Component
public class NettyServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private static final int PORT = 8000;
    @PostConstruct
    public void start() {

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                    ch.pipeline()
                            //HTTP编解码器(握手时需要)
                            .addLast(new HttpServerCodec())
                            //HTTP消息聚合
                            .addLast(new HttpObjectAggregator(1024 * 64))
                            // 大数据流处理器
                            .addLast(new ChunkedWriteHandler())
                            //Websocket数据压缩处理器
                            .addLast(new WebSocketServerCompressionHandler())
                            //开启Websocket
                            .addLast(new WebSocketServerProtocolHandler("/",null ,true,1024*10))
                            //自定义解码器：把传入的Websocket帧转换成ByteBuf
                            .addLast(new WebSocketProtobufDecoder())
                            //再把ByteBuf转换成ProtoBuf
                            .addLast(new ProtobufDecoder(MessageProto.ImMessage.getDefaultInstance()))
                            //登录校验
//                            .addLast(new LoginRequestHandler())
                            //登录校验热插拔处理器
//                            .addLast(new AuthHandler())
                            //消息处理Handler
                            .addLast(new MessageRequestHandler())
                            //自定义编码器：把ProtoBuf转换成ByteBuf并包装成Websocket帧
                            .addLast(new WebSocketProtobufEncoder());
                    }
                });
        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
                //重新尝试,递增绑定端口
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
