package com.cloud.office.customer.busi.netty.utils;

import com.cloud.office.customer.busi.netty.attribute.Attributes;
import com.cloud.office.customer.busi.service_im.entity.Session;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZuoHaoFan
 * @Description: 在线会话管理器
 * @date 2023/5/11 14:46
 */

public class SessionManager {
    private static final SessionManager instance = new SessionManager();
    private final Map<Integer, Session> sessionMap = new ConcurrentHashMap<>();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return instance;
    }

    // 创建会话
    public void createSession(Integer sessionId, Integer visitorId, String visitorKey, Integer serverId, String serverKey, Channel channel) {
        Session session = new Session(sessionId, visitorId, visitorKey, serverId, serverKey);
        sessionMap.put(sessionId, session);
    }

    // 获取会话
    public Session getSession(Integer sessionId) {
        return sessionMap.get(sessionId);
    }

    // 更新会话密钥
    public void updateSessionKey(Integer sessionId, String key) {
        Session session = getSession(sessionId);
        if (session != null) {
            session.setVisitorKey(key);
        }
    }


    // 移除会话
    public void removeSession(List<Integer> sessionIdList, Channel channel) {
        sessionIdList.forEach(sessionId->{
            sessionMap.remove(sessionId);
            channel.attr(Attributes.CONVERSATION_ATTRIBUTE_KEY).get().remove(sessionId);
        });
    }

    //判读是否存在会话
    public boolean hasSession(Integer sessionId) {
        return sessionMap.containsKey(sessionId);
    }

    //获取所有的Session
    public Map<Integer, Session> getAllSession(){
        return sessionMap;
    }
}
