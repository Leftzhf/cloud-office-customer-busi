package com.cloud.office.customer.busi.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/13 16:37
 */
@Component
public class JwtAuthenticationGatewayFilter implements GatewayFilter, Ordered {



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 获取请求头中的token值
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 判断是否携带token,如果有则请求认证中心认证
        if (StringUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {

//            authClient.validateToken(request.getNativeRequest());
        }else {
            // 抛出访问拒绝异常
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
