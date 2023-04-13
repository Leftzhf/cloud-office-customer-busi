package com.cloud.office.customer.busi;

import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/3 18:07
 */
@FeignClient(name = "service-usercenter")
public interface ServiceUsercenterClient {


    @PostMapping("/findUserByUsername")
    User findByUsername(@RequestParam String username);
    @PostMapping("/register")
    void register(@RequestBody RegisterUserDto registerUserDto);

    @GetMapping("/info")
    UserVo findUserInfoByUsername(@RequestParam String username);
}
