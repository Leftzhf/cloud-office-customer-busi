package com.cloud.office.customer.busi.config;

import com.cloud.office.customer.busi.jwt.CustomJwtAccessTokenConverter;
import com.cloud.office.customer.busi.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/13 23:26
 */
@Configuration
public class AccessTokenConfig {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Bean
    TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        converter.setSigningKey(jwtTokenUtils.getSecret());
        return converter;
    }

}
