package com.cloud.office.customer.busi.config;

import com.cloud.office.customer.busi.jwt.CustomJwtAccessTokenConverter;
import com.cloud.office.customer.busi.jwt.JwtUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collections;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/13 14:26
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    JwtUserServiceImpl userDetailsServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    @Lazy
    CustomJwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    TokenStore tokenStore;

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("111111"))
                .redirectUris("https://www.baidu.com")
                .scopes("all")//授权范围
                .autoApprove(true)//自动授权，返回验证码
                .authorizedGrantTypes("authorization_code","password");
    }

    /**
     * 配置授权端点
     * 密码模式必须配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //密码模式需要配置
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl)
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
//        super.configure(security);
    }

    @Bean
    AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setTokenStore(tokenStore);
        services.setAuthenticationManager(authenticationManager);
        services.setSupportRefreshToken(true);
//        services.setTokenEnhancer(jwtAccessTokenConverter);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
        services.setAccessTokenValiditySeconds(60*60*2);
        services.setRefreshTokenValiditySeconds(60 * 60*24*3);
        return services;
    }


}
