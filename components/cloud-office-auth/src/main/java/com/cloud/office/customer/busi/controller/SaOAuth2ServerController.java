package com.cloud.office.customer.busi.controller;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.dev33.satoken.util.SaTokenConsts;
import com.cloud.office.customer.busi.constant.Constants;
import com.cloud.office.customer.busi.service.Impl.SysLoginService;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.LoginBody;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/4 22:37
 */
/**
 * Sa-OAuth2 Server端 控制器
 */
@RestController
public class SaOAuth2ServerController {


    @Autowired
    SysLoginService sysLoginService;
    @Autowired
    public void setSaOAuth2Config(SaOAuth2Config cfg) {
        cfg.
                // 配置：未登录时返回的View
                        setNotLoginView(() -> {
                    String msg = "当前会话在OAuth-Server端尚未登录，请先访问"
                            + "<a href='/oauth2/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>"
                            + "进行登录之后，刷新页面开始授权";
                    return msg;
                }).
                // 配置：登录处理函数
                        setDoLoginHandle((name, pwd) -> {
                    if (sysLoginService.login(name, pwd)) {
                        return SaResult.ok();
                    } else {
                        return SaResult.error("账号名或密码错误");
                    }
                }). // 配置：确认授权时返回的View
                setConfirmView((clientId, scope) -> {
            String msg = "<p>应用 " + clientId + " 请求授权：" + scope + "</p>"
                    + "<p>请确认：<a href='/oauth2/doConfirm?client_id=" + clientId + "&scope=" + scope + "' target='_blank'> 确认授权 </a></p>"
                    + "<p>确认之后刷新页面</p>";
            return msg;
        })
        ;
    }
    // 处理所有OAuth相关请求
    @RequestMapping("/oauth2/*")
    public Object request() {
        System.out.println("------- 进入请求: " + SaHolder.getRequest().getUrl());
        return SaOAuth2Handle.serverRequest();
    }

    /**
     * 登出方法
     */
    @DeleteMapping("/logout")
    public boolean logout() {
        sysLoginService.logout();
        return true;
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Boolean register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        sysLoginService.register(registerBody);
        return true;
    }
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}

