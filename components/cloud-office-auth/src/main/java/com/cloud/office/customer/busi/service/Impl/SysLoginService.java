package com.cloud.office.customer.busi.service.Impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.constant.Constants;
import com.cloud.office.customer.busi.domain.vo.RegisterBody;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.MessageUtils;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * 登录校验方法
 *
 * @author leftleft
 */
@Service
public class SysLoginService {
    /**
     *
     * 登录
     */
    @Autowired
    ServiceUsercenterClient sysLoginService;
    public String login(String username, String password) {
        TCostomerServerUser userInfo = sysLoginService.getUserByName(username);
//        checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, userInfo.getPassword()));
        // 获取登录token
//        LoginHelper.loginByDevice(userInfo, DeviceType.PC);
        StpUtil.login(userInfo.getId());
        return StpUtil.getTokenValue();
    }



    /**
     * 退出登录
     */
    public void logout() {
        try {
            StpUtil.logout();
        } catch (NotLoginException ignored) {
        }
    }

    /**
     * 注册
     */
    public void register(RegisterBody registerBody) {
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();

        // 注册用户信息
        TCostomerServerUser userInfo = sysLoginService.getUserByName(username);
        userInfo.setUsername(username);
        userInfo.setPassword(BCrypt.hashpw(password));
        boolean regFlag = sysLoginService.registerUserInfo(userInfo);
        if (!regFlag) {
            throw new UserException("user.register.error");
        }
    }

}
