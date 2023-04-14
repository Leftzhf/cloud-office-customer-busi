package com.cloud.office.customer.busi.config;

import com.cloud.office.customer.busi.filter.JwtAuthenticationGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/13 16:37
 */
@Configuration
public class GatewayConfig {

    @Bean
    public JwtAuthenticationGatewayFilter jwtAuthenticationGatewayFilter() {
        return new JwtAuthenticationGatewayFilter();
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, JwtAuthenticationGatewayFilter jwtAuthenticationGatewayFilter) {
        return builder.routes()
                // 添加需要进行认证的路由配置
                .route("auth_route", r -> r
                        .path("/auth/**")
                        .filters(f -> f.filter(jwtAuthenticationGatewayFilter))
                        // 认证中心的URL
                        .uri("http://localhost:8902"))
                // 添加其他路由配置
                .build();
    }
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}

