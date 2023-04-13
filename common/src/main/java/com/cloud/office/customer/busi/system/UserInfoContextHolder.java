//package com.cloud.office.customer.busi.system;
//
//import cn.hutool.system.UserInfo;
//import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
//
///**
// * @author ZuoHaoFan
// * @Description: new java files header..
// * @date 2023/3/17 19:23
// */
//public class UserInfoContextHolder {
//    private static ThreadLocal<TCostomerServerUser> userInfo = new ThreadLocal();
//    private static ThreadLocal<String> tenant = new ThreadLocal();
//
//    public UserInfoContextHolder() {
//    }
//
//    public static TCostomerServerUser getUser() {
//        return (TCostomerServerUser)userInfo.get();
//    }
//
//    public static void setUser(TCostomerServerUser user) {
//        userInfo.set(user);
//    }
//
//    public static String getTenant() {
//        return (String)tenant.get();
//    }
//
//    public static void setTenant(String tenantId) {
//        tenant.set(tenantId);
//    }
//
//    public static void clear() {
//        tenant.remove();
//        userInfo.remove();
//    }
//}