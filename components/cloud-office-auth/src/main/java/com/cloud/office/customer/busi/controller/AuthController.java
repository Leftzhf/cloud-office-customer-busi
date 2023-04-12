package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.LoginUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//todo 集成到认证网关
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginUserDto loginUserDto) {
        log.info("登录用户:{}", loginUserDto.toString());
        String token = userService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        return ResultVo.success(token);
    }

    @PostMapping("/register")
    public ResultVo register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("注册用户:{}", registerUserDto.toString());
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setEmail(registerUserDto.getEmail());
        user.setNickname(registerUserDto.getNickname());
        userService.register(user);
        return ResultVo.success();
    }

    @PostMapping("/refresh")
    public ResultVo refresh(@RequestHeader("${jwt.tokenHeader}") String token) {
        log.info("刷新token:{}", token);
        return ResultVo.success(userService.refreshToken(token));
    }

}
