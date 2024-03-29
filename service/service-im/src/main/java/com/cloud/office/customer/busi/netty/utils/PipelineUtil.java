package com.cloud.office.customer.busi.netty.utils;

import com.cloud.office.customer.busi.netty.handler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelPipeline;

/**
 * ChannelPipeline工具类
 *
 * @author leftleft
 * @date 2023-04-22
 */
@Component
public class PipelineUtil {

    /**
     * 心跳检测
     */
    @Autowired
    private HeartBeatRequestHandler heartBeatRequestHandler;

    /**
     * 身份校验
     */
    @Autowired
    private AuthHandler authHandler;

    /**
     * 登录
     */
    @Autowired
    private LoginRequestHandler loginRequestHandler;

    /**
     * 退出登录
     */
    @Autowired
    private LogoutRequestHandler logoutRequestHandler;

    /**
     * 单聊消息
     */
    @Autowired
    private MessageRequestHandler messageRequestHandler;

    @Autowired
    private SecondHandShakeHandler secondHandShakeHandler;

    /**
     * 添加websocket/tcp通用handler
     *
     * @param pipeline
     */
    public void addHandler(ChannelPipeline pipeline) {
        pipeline.addLast(
                heartBeatRequestHandler,
                secondHandShakeHandler,
                loginRequestHandler,
                authHandler,
                logoutRequestHandler,
                messageRequestHandler
        );
    }

}
