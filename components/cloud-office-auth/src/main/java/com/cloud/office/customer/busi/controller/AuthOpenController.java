package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.jwt.JwtTokenUtils;
import com.cloud.office.customer.busi.service.AuthService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.LoginUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Tag(name = "AuthOpenController", description = "开放认证接口")
@RequestMapping("/auth")
public class AuthOpenController {

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
        String token = authService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        return ResultVo.success(token);
    }

    @PostMapping("/register")
    @Operation(description = "注册")
    public ResultVo register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("注册用户:{}", registerUserDto.toString());
        authService.register(registerUserDto);
        return ResultVo.success();
    }

    @PostMapping("/refresh")
    @Operation(description = "刷新token")
    public ResultVo refresh(@RequestHeader("${jwt.tokenHeader}") String token) {
        log.info("刷新token:{}", token);
        return ResultVo.success(authService.refreshToken(token));
    }

    @PostMapping("/validateToken")
    @Operation(description = "刷新token")
    public ResultVo validateToken(String token) {
//        log.info("校验token:{}", );
        String usernameFromToken = jwtTokenUtils.getUsernameFromToken(token);
        log.info("校验token:{}", usernameFromToken);
        return ResultVo.success();
    }

}
