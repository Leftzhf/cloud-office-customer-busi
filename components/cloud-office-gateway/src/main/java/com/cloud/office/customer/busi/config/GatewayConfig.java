package com.cloud.office.customer.busi.config;

import com.cloud.office.customer.busi.filter.JwtAuthenticationGatewayFilterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
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
@EnableWebFluxSecurity
public class GatewayConfig {
    @Value("${jwt.secret}") // 配置JWK Set URI
    private String secretKey;

    //注册过滤器

    @Bean
    public JwtAuthenticationGatewayFilterFactory jwtAuthenticationGatewayFilterFactory() {
        return new JwtAuthenticationGatewayFilterFactory();
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

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/actuator/**","/auth/**","/user/**").permitAll()
                .anyExchange().authenticated().and().csrf().disable();
        return http.build();
    }
}

