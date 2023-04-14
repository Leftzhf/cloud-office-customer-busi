package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.service.AuthService;
import com.cloud.office.customer.busi.service.Impl.JwtUser;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/13 12:01
 */
@Slf4j
@RestController
@Tag(name = "AuthController",description = "授权接口")
@RequestMapping("/open/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ServiceUsercenterClient usercenterClient;
    /**
     * 获取当前用户详细信息
     *
     * @return
     */
    @GetMapping("/info")
    @Operation(description = "获取当前登录用户的权限信息")
    public ResultVo getUserInfo() {
        log.info("获取当前认证的用户权限信息");
        // 在 com.kefu.admin.common.jwt.JwtAuthenticationTokenFilter 里设置了principal为jwtUser
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser jwtUser = (JwtUser) principal;
        UserVo userVo = usercenterClient.findUserInfoByUsername(jwtUser.getUsername());
        return ResultVo.success(userVo);
    }
    @PostMapping("/jwtAuthorize")
    @Operation(description = "jwt校验")
    public ResultVo jwtAuthorize(@RequestBody RegisterUserDto registerUserDto) {
        log.info("校验成功用户:{}", registerUserDto.toString());
        return ResultVo.success();
    }
}
