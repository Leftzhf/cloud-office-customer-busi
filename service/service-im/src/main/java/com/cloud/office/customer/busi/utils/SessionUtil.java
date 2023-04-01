package com.cloud.office.customer.busi.utils;

import com.cloud.office.customer.busi.imserver.attribute.Attributes;
import com.cloud.office.customer.busi.imserver.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    //维护一个在线用户id和Channel的map
    //TODO session在redis内维护
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    /**
     * 绑定会话和用户id
     *
     * @param session 会话
     * @param channel 通道
     */
    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 解除绑定会话和用户id
     *
     * @param channel 通道
     */
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {

        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);
    }
}
