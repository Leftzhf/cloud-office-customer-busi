package com.cloud.office.customer.busi.service.Impl;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/6 00:48
 */

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        //todo  返回此 loginId 拥有的权限列表
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // todo 返回此 loginId 拥有的角色列表
        return null;
    }

}
