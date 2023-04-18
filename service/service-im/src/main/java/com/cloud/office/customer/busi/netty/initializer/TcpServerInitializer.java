package com.cloud.office.customer.busi.netty.initializer;

import com.cloud.office.customer.busi.netty.codec.TcpPacketCodec;
import com.cloud.office.customer.busi.netty.codec.TcpSpliter;
import com.cloud.office.customer.busi.netty.handler.MyIdleStateHandler;
import com.cloud.office.customer.busi.netty.utils.PipelineUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * tcp连接初始化Channel，给Channel关联的pipeline添加handler
 *
 */
@Component
public class TcpServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Autowired
    private PipelineUtil pipelineUtil;

    @Autowired
    private TcpPacketCodec tcpPacketCodec;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 空闲检测
        pipeline.addLast(new MyIdleStateHandler());
        // 处理粘包半包
        pipeline.addLast(new TcpSpliter());
        // 数据包编解码器
        pipeline.addLast(tcpPacketCodec);

        // 添加tcp/websocket通用handler
        pipelineUtil.addHandler(pipeline);

    }
}
