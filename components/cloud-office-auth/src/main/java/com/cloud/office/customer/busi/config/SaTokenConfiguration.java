package com.cloud.office.customer.busi.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cloud.office.customer.busi.service.Impl.SysLoginService;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Action;

@AutoConfiguration
public class SaTokenConfiguration {

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    @Autowired
    SysLoginService sysLoginService;

}