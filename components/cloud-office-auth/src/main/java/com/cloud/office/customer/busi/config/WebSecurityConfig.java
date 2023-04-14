package com.cloud.office.customer.busi.config;

import com.cloud.office.customer.busi.jwt.JwtAuthenticationEntryPoint;
import com.cloud.office.customer.busi.jwt.JwtAuthenticationTokenFilter;
import com.cloud.office.customer.busi.jwt.JwtUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUserServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * 这里记住一定要重新父类的对象，不然在注入 AuthenticationManager时会找不到，默认spring security 没有给我们注入到容器中
     *
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 用户登录密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用跨站请求保护，开启跨域
                .cors().and().csrf().disable()
                //设置未认证请求的处理端点
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //配置会话管理策略为无状态（STATELESS），这意味着不会在服务器端保存任何会话状态，而是依赖于每个请求中的认证信息。
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //开始配置具体的权限规则。
                .and().authorizeRequests()
                //配置允许所有的 HTTP OPTIONS 请求访问任何路径，用于处理 CORS 预检请求。
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //以下路径配置放行
                .antMatchers("/open/auth/**",
                        "/auth/**",
                        "/oauth/**",
                        "/actuator/**",
                        "/common/**",
                        "/images/**",
                        "/v2/api-docs",
                        "/doc.html",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/configuration/ui",
                        "/swagge‌​r-ui.html").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //其他请求需要认证
                .anyRequest().authenticated()
                //放行表单提交
//                .and().formLogin().permitAll()
                //配置 HTTP 响应头的缓存
                .and().headers().cacheControl();

        // 注入jwt过滤器,在UsernamePasswordAuthenticationFilter之前进行jwt校验认证
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
