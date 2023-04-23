package com.cloud.office.customer.busi.service.Impl;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.exception.user.UserExistsException;
import com.cloud.office.customer.busi.jwt.JwtUserServiceImpl;
import com.cloud.office.customer.busi.service.AuthService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
    public OAuth2AccessToken  login(String username, String password) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        RestTemplate restTemplate=new RestTemplate();
        MultiValueMap<String,Object> paramsMap=new LinkedMultiValueMap<>();
        paramsMap.set("username",username);
        paramsMap.set("password",password);
        paramsMap.set("grant_type","password");
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("client","111111"));
        OAuth2AccessToken token=restTemplate.postForObject("http://localhost:8902/oauth/token",paramsMap,OAuth2AccessToken.class);
//        return jwtTokenUtils.generateToken(jwtUser);
        return token;
    }

    /**
     * 刷新token todo 调Oauth2刷新token
     *
     * @param oldToken 旧token
     * @return
     */
    @Override
    public String refreshToken(String oldToken) {
//        RestTemplate restTemplate=new RestTemplate();
//        MultiValueMap<String,Object> paramsMap=new LinkedMultiValueMap<>();
//        paramsMap.set("username",username);
//        paramsMap.set("password",password);
//        paramsMap.set("grant_type","password");
//        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("client","111111"));
//        OAuth2AccessToken token=restTemplate.postForObject("http://localhost:8902/oauth/token",paramsMap,OAuth2AccessToken.class);
        return null;
    }
//
//    @Override
//    public boolean validateToken(HttpServletRequest request) {
//        // 获取请求头中的token值
//        String authHeader = request.getHeader(jwtTokenUtils.getTokenHeader());
//        // 获取token
//        String token = authHeader.substring(jwtTokenUtils.getTokenHead().length());
//        log.info("token:{}", token);
//        // 获取用户名
//        String username = jwtTokenUtils.getUsernameFromToken(token);
//        // 校验token
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // TODO: 后面从Redis缓存中获取，不然每次请求都会去查询用户
//            JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
//            if (jwtTokenUtils.validateToken(token, jwtUser)) {
//                log.info("token有效");
//                //如果token有效，就加入authentication，代表已经认证过了
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                return true;
//            }
//        }
//        return false;
//    }
}
