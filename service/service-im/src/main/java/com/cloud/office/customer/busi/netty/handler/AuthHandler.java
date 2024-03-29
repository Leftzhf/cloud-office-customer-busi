package com.cloud.office.customer.busi.netty.handler;

import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 鉴权逻辑处理器
 *
 *
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!ChannelUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // pipeline的热拔插，移除handler
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (ChannelUtil.hasLogin(ctx.channel())) {
            log.info("当前连接登录验证完毕，无需再次验证，AuthHandler 被移除");
        } else {
            log.info("无登录验证，强制关闭连接！");
        }
    }
}
