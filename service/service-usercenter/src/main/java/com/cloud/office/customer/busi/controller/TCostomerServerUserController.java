package com.cloud.office.customer.busi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.office.customer.busi.service.ITCostomerServerUserService;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerRule;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  用户中心
 * </p>
 *
 * @author leftleft
 * @since 2023-03-17
 */
@RestController
@Api(tags = "用户中心")
@RequestMapping("/user")
public class TCostomerServerUserController {

    @Autowired
    private ITCostomerServerUserService tCostomerServerUser;
    //根据用户名查询用户
    @PostMapping("/getUserByName")
    @ApiOperation(value = "根据用户名查询用户信息")
    public TCostomerServerUser getUserByName(String username){
        return  tCostomerServerUser.getUserByName(username);
    }
    @PostMapping("/getRuleByUserId")
    @ApiOperation(value = "根据userID查询用户权限")
    public Map<String, List<TCostomerServerRule>> getRuleByUserId(String userId){
        return tCostomerServerUser.getRuleByUserId(userId);
    }

    @PostMapping("/registerUserInfo")
    @ApiOperation(value = "注册用户")
    public Integer registerUserInfo(@RequestBody TCostomerServerUser userinfo){
        return tCostomerServerUser.registerUserInfo(userinfo);
    }

}
