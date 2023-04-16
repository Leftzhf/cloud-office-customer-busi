package com.cloud.office.customer.busi;

import com.cloud.office.customer.busi.vo.PageVo;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/3 18:07
 */
@FeignClient(name = "service-usercenter")
public interface ServiceUsercenterClient {

    @GetMapping("/user/getUserById/{userId}")
    User getUserById(@PathVariable Integer userId);
    @PostMapping("/user/findUserByUsername")
    User findByUsername(@RequestParam String username);
    @PostMapping("/user/register")
    void register(@RequestBody RegisterUserDto registerUserDto);

    @GetMapping("/user/info")
    UserVo findUserInfoByUsername(@RequestParam String username);

    @PostMapping("/user")
    void addUser(@RequestBody UserDto userDto);

    @PutMapping("/getById")
    User getById(@RequestParam Integer id);

    @PostMapping("/list")
    PageVo<User> findUserPageList(@RequestBody UserPageDto userPageDto);

}
