package com.cloud.office.customer.busi.service.Impl;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.common.exception.user.UserExistsException;
import com.cloud.office.customer.busi.jwt.JwtTokenUtils;
import com.cloud.office.customer.busi.jwt.JwtUserServiceImpl;
import com.cloud.office.customer.busi.service.AuthService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/12 15:11
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtUserServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceUsercenterClient usercenterClient;

    @Override
    public void register(RegisterUserDto registerUserDto) throws UserExistsException {
        //把密码加密
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        //调用户中心 注册
       usercenterClient.register(registerUserDto);

    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回token
     */
    @Override
    public String login(String username, String password)throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        // TODO: 后面将jwtUser加入Redis缓存
        return jwtTokenUtils.generateToken(jwtUser);
    }

    /**
     * 刷新token
     *
     * @param oldToken 旧token
     * @return
     */
    @Override
    public String refreshToken(String oldToken) {
        if (!StringUtils.isEmpty(oldToken)) {
            String token = oldToken.substring(jwtTokenUtils.getTokenHead().length());
            return jwtTokenUtils.refreshToken(token);
        }
        return null;
    }

    @Override
    public boolean validateToken(HttpServletRequest request) {
        // 获取请求头中的token值
        String authHeader = request.getHeader(jwtTokenUtils.getTokenHeader());
        // 获取token
        String token = authHeader.substring(jwtTokenUtils.getTokenHead().length());
        log.info("token:{}", token);
        // 获取用户名
        String username = jwtTokenUtils.getUsernameFromToken(token);
        // 校验token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // TODO: 后面从Redis缓存中获取，不然每次请求都会去查询用户
            JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtils.validateToken(token, jwtUser)) {
                log.info("token有效");
                //如果token有效，就加入authentication，代表已经认证过了
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return true;
            }
        }
        return false;
    }
}
