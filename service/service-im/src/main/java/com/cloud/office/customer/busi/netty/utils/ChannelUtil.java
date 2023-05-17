package com.cloud.office.customer.busi.netty.utils;

import com.cloud.office.customer.busi.netty.attribute.Attributes;
import com.cloud.office.customer.busi.service_im.entity.Session;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * channel工具类:保存在线状态
 * TODO 后期改成存到Redis里面
 *
 * @author leftleft
 * @date 2023-04-21
 */
@Slf4j
public class ChannelUtil {

    /**
     * useId -> Channel 的映射集合
     */
    public static final Map<Integer, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();
    /**
     * 登录成功后缓存【用户 -> 用户连接】的映射关系
     *
     * @param user    用户对象
     * @param channel 连接
     */
    public static void bindUser(User user, Channel channel, Integer conversationId, String secretKey) {
        log.info("缓存【username:channel】映射,username={},channel={},会话id={}", user.getUsername(), channel.toString(), conversationId);
        USER_ID_CHANNEL_MAP.put(user.getId(), channel);
        channel.attr(Attributes.SECRET_KEY).set(secretKey);
        channel.attr(Attributes.USER_ATTRIBUTE_KEY).set(user);
    }

    /**
     * 退出登录或断开连接后清除【用户 -> 用户连接】的映射关系
     *
     * @param channel 连接
     */
    public static void unBindUser(Channel channel) {
        if (hasLogin(channel)) {
            log.info("移除【username:channel】映射,username={},channel={}", getUser(channel).getUsername(), channel.toString());
            USER_ID_CHANNEL_MAP.remove(getUser(channel).getId());
            channel.attr(Attributes.USER_ATTRIBUTE_KEY).set(null);
            channel.attr(Attributes.SECRET_KEY).set(null);
        }
    }

    /**
     * 根据连接判断是否已经握手
     *
     * @param channel 连接
     * @return true 则表示已登录
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.USER_ATTRIBUTE_KEY);
    }

    /**
     * 根据连接获取用户对象
     *
     * @param channel 连接
     * @return 会话身份信息
     */
    public static User getUser(Channel channel) {
        return channel.attr(Attributes.USER_ATTRIBUTE_KEY).get();
    }

    public static List<Integer> getConversationId(Channel channel) {
        Map<Integer, Session> integerSessionMap = channel.attr(Attributes.CONVERSATION_ATTRIBUTE_KEY).get();
        //返回integerSessionMap的键组成的list
        List<Integer> collect = integerSessionMap.keySet().stream().collect(Collectors.toList());
        return collect;
    }
    /**
     * 根据userId获取连接
     *
     * @param username 用户名
     * @return 连接
     */
    public static Channel getChannel(Integer userId) {
        return USER_ID_CHANNEL_MAP.get(userId);
    }

    /**
     * 根据连接获取密钥
     *
     * @param channel 通道
     * @return {@link String}
     */
    public static String getSecretKey(Channel channel) {
        return channel.attr(Attributes.SECRET_KEY).get();
    }

    //根据id获得密钥
    public static String getSecretKey(Integer id) {

        return USER_ID_CHANNEL_MAP.get(id).attr(Attributes.SECRET_KEY).get();
    }

    //根据channel获取用户id
    public static Integer getId(Channel channel) {
        return getUser(channel).getId();
    }
}
