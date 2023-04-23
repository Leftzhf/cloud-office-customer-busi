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
     * 保证异步情况下是唯一的,确保线程安全
     */
    private static ThreadLocal<User> current_point = new ThreadLocal<>();

    /**
     * 轮询分配
     *
     * @param userList 用户列表
     * @return {@link User}
     */
    public static User getServerByPolling(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        User currentPoint = current_point.get();
        if (currentPoint == null) {
            // 第一次调用，将current_point指向链表头
            currentPoint = userList.get(0);
            current_point.set(currentPoint);
        } else {
            // 将current_point移动到下一个元素
            int currentIndex = userList.indexOf(currentPoint);
            currentIndex = (currentIndex + 1) % userList.size();
            currentPoint = userList.get(currentIndex);
            current_point.set(currentPoint);
        }

        return currentPoint;
    }
}
