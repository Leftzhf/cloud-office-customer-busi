package com.cloud.office.customer.busi.utils;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;

import java.util.List;

/**
 * 动态客服分配器
 *
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/18 08:59
 */
public class ServerDistributionUtil {

    /**
     * 当前轮询客服编号
     */
    private static int currentServerIndex = 0;

    /**
     * 轮询分配-循环链表
     *
     * @param userList 在线客服列表
     * @return {@link User}
     */
    public static User getServerByPolling(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        // 计算下一个客服的编号
        int nextServerIndex = (currentServerIndex + 1) % userList.size();
        // 获取下一个客服
        User nextServer = userList.get(nextServerIndex);
        // 更新当前轮询客服编号
        currentServerIndex = nextServerIndex;
        return nextServer;
    }
}
