package com.cloud.office.customer.busi.filter;

import com.cloud.office.customer.busi.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Slf4j
public class JwtAuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    JwtAccessTokenConverter accessTokenConverter;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private static final String REDIS_TOKEN_KEY = "TOKEN:access:"; // Redis 中保存 Token 的 key 前缀
    private static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;
    private static final String BEARER_TOKEN_TYPE = "Bearer ";

    public JwtAuthenticationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 获取请求头中的token值
            String authHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);
            // 判断是否携带token,如果有则比对redis
            if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(BEARER_TOKEN_TYPE)) {
                //比对redis，通过则放到header里并放行
                String token = authHeader.substring(jwtTokenUtils.getTokenHead().length());
                String key = REDIS_TOKEN_KEY + token;
                if (stringRedisTemplate.hasKey(key)) {
                    Claims claimsToken = jwtTokenUtils.getClaimsToken(token);
                    String username = (String) claimsToken.get("user_name");
                    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().header("User-Agent", username).build();
                    log.info("放行请求{},{}", modifiedRequest.getURI().getPath());
                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                }else {
                    //token 无效或已过期，拒接访问
                    log.info("token 无效或已过期，拒接访问");
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }
            } else {
                // 没有携带token，抛出访问拒绝异常
                log.info("未携带token，拒绝访问");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        };
    }

    public static class Config {
        // 这里可以定义配置属性
    }

}
