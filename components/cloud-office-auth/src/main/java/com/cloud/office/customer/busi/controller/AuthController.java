package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.util.JwtTokenUtils;
import com.cloud.office.customer.busi.vo.ResultVo;
import com.cloud.office.customer.busi.service.AuthService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.LoginUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Tag(name = "AuthOpenController", description = "开放认证接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    private ServiceUsercenterClient usercenterClient;
    @PostMapping("/login")
    @Operation(description = "登录")
    public ResultVo login(@RequestBody LoginUserDto loginUserDto) {
        log.info("登录用户:{}", loginUserDto.toString());
        OAuth2AccessToken token = authService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        return ResultVo.success(token);
    }

    @PostMapping("/register")
    @Operation(description = "注册")
    public ResultVo register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("注册用户:{}", registerUserDto.toString());
        authService.register(registerUserDto);
        return ResultVo.success();
    }
//
//    @PostMapping("/refresh")
//    @Operation(description = "刷新token")
//    public ResultVo refresh(@RequestHeader("${jwt.tokenHeader}") String token) {
//        log.info("刷新token:{}", token);
//        return ResultVo.success(authService.refreshToken(token));
//    }
}
