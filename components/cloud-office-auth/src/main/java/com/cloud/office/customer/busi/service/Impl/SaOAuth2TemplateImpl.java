package com.cloud.office.customer.busi.service.Impl;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/5 23:49
 */

import cn.dev33.satoken.oauth2.logic.SaOAuth2Template;
import cn.dev33.satoken.oauth2.model.SaClientModel;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;

/**
 * Sa-Token OAuth2.0 整合实现
 */
@Component
public class SaOAuth2TemplateImpl extends SaOAuth2Template {

    // 根据 id 获取 Client 信息
    @Override
    public SaClientModel getClientModel(String clientId) {
        // 此为模拟数据，真实环境需要从数据库查询
        if("1001".equals(clientId)) {
            return new SaClientModel()
                    .setClientId("1001")
                    .setClientSecret("aaaa-bbbb-cccc-dddd-eeee")
                    .setAllowUrl("*")
                    .setContractScope("userinfo")
                    .setIsAutoMode(true)
                    .setIsPassword(true);
        }
        return null;
    }

    // 根据ClientId 和 LoginId 获取openid
    @Override
    public String getOpenid(String clientId, Object loginId) {
        // 此为模拟数据，真实环境需要从数据库查询
        return "gr_SwoIN0MC1ewxHX_vfCW3BothWDZMMtx__";
    }

    /**
     * 统一资源和会话令牌
     *
     * @param clientId 客户机id
     * @param loginId  登录id
     * @param scope    范围
     * @return {@link String}
     */
    @Override
    public String randomAccessToken(String clientId, Object loginId, String scope) {
        String tokenValue = StpUtil.createLoginSession(loginId);
        return tokenValue;
    }

}
