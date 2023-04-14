package com.cloud.office.customer.busi.jwt;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/14 00:40
 */

import com.cloud.office.customer.busi.service.Impl.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Autowired
    JwtUserServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 获取用户名
        String username = authentication.getName();
        // 根据用户名从 UserDetailsService 加载用户实体
        JwtUser userDetails = (JwtUser)userDetailsService.loadUserByUsername(username);
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("userInfo",userDetails);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(objectObjectHashMap);
        // 将用户实体的信息添加到令牌中
        return super.enhance(accessToken, authentication);
    }
}
