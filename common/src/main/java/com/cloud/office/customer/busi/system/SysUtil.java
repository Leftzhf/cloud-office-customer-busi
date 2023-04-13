//package com.cloud.office.customer.busi.system;
//
//import cn.hutool.system.UserInfo;
//import com.cloud.office.customer.busi.enums.UserIdEnum;
//import com.cloud.office.customer.busi.exception.ApplicationException;
//import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.Locale;
//
///**
// * @author ZuoHaoFan
// * @Description: new java files header..
// * @date 2023/3/17 19:22
// */
//public class SysUtil {
//    public SysUtil() {
//    }
//
//    public static String getTenantId() {
//        if (StringUtils.isNotBlank(UserInfoContextHolder.getTenant())) {
//            return UserInfoContextHolder.getTenant();
//        } else if (getCurrentUser() != null) {
//            return getCurrentUser().getTenantId();
//        } else {
//            throw new ApplicationException("租户id未设置!");
//        }
//    }
//
//    public static TCostomerServerUser getCurrentUser() {
//        return UserInfoContextHolder.getUser();
//    }
//
//    public static String getCurrentUserId() {
//        return getCurrentUser() == null ? UserIdEnum.unknown.getCode() : getCurrentUser().getId();
//    }
//
//    public static String getCurrentUserName() {
//        return getCurrentUser() == null ? UserIdEnum.unknown.getValue() : getCurrentUser().getUsername();
//    }
//
//
//}
//
