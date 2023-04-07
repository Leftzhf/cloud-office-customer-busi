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
import com.cloud.office.customer.busi.domain.vo.LoginBody;
import com.cloud.office.customer.busi.domain.vo.RegisterBody;
import com.cloud.office.customer.busi.service.Impl.SysLoginService;
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

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    @Autowired
    SysLoginService sysLoginService;
    // 处理所有OAuth相关请求
    @RequestMapping("/oauth2/*")
    public Object request() {
        System.out.println("------- 进入请求: " + SaHolder.getRequest().getUrl());
        return SaOAuth2Handle.serverRequest();
    }

    @RequestMapping({"/", "/index"})
    public String index() {
        String str = "<br />"
                + "<h1 style='text-align: center;'>资源页 （登录后才可进入本页面） </h1>"
                + "<hr/>"
                + "<p style='text-align: center;'> Sa-Token " + SaTokenConsts.VERSION_NO + " </p>";
        return str;
    }
    /**
     * 登出方法
     */
    @DeleteMapping("logout")
    public boolean logout() {
        sysLoginService.logout();
        return true;
    }

    /**
     * 用户注册
     */
    @PostMapping("register")
    public Boolean register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        sysLoginService.register(registerBody);
        return true;
    }

    @PostMapping("login")
    public Map<String, Object> login(@Validated @RequestBody LoginBody form) {
        // 用户登录
        String accessToken = sysLoginService.login(form.getUsername(), form.getPassword());

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put(Constants.ACCESS_TOKEN, accessToken);
        return rspMap;
    }

}

