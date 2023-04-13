package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.service.AuthService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.LoginUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "开放认证接口")
@RequestMapping("/auth/open")
public class AuthOpenController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ServiceUsercenterClient usercenterClient;
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultVo login(@RequestBody LoginUserDto loginUserDto) {
        log.info("登录用户:{}", loginUserDto.toString());
        String token = authService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        return ResultVo.success(token);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public ResultVo register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("注册用户:{}", registerUserDto.toString());
        authService.register(registerUserDto);
        return ResultVo.success();
    }


}
