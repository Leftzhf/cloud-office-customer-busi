package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.service.UserService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/12 15:18
 */
@RestController
public class UserClientController {
    @Autowired
    private UserService userService;

    @PostMapping("/findUserByUsername")
    User findByUsername(@RequestParam String username){
        return userService.findByUsername(username);
    }

    @PostMapping("/register")
    void register(@RequestBody RegisterUserDto registerUserDto){
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setEmail(registerUserDto.getEmail());
        user.setNickname(registerUserDto.getNickname());
        userService.register(user);
    }
    /**
     * 获取当前用户详细信息
     *
     * @return
     */
    @GetMapping("/info")
    public UserVo getUserInfo(@RequestParam String username) {
        UserVo userVo = userService.findUserInfoByUsername(username);
        return userVo;
    }

}
