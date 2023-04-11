package com.cloud.office.customer.busi.service.Impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.common.ResponseData;
import com.cloud.office.customer.busi.common.exception.ApplicationException;
import com.cloud.office.customer.busi.constant.Constants;
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
    public Boolean login(String username, String password) {
        ResponseData<TCostomerServerUser> userByName = sysLoginService.getUserByName(username);
        TCostomerServerUser userInfo = userByName.getData();
        if(BCrypt.checkpw(password, userInfo.getPassword())){
            StpUtil.login(userInfo.getId(), SaLoginConfig.setExtra("userId",userInfo.getId()).setExtra("userName",userInfo.getUsername()));
            return true;
        }else {
            return false;
        }

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
        ResponseData<TCostomerServerUser> userByName = sysLoginService.getUserByName(username);
        TCostomerServerUser userInfo = userByName.getData();
        userInfo.setUsername(username);
        userInfo.setPassword(BCrypt.hashpw(password));
        ResponseData<Integer> integerResponseData = sysLoginService.registerUserInfo(userInfo);
//        if (!regFlag) {
//            throw new ApplicationException("注册失败！");
//        }
    }

}
