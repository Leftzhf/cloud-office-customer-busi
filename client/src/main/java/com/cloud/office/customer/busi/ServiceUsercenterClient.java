package com.cloud.office.customer.busi;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerRule;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/3 18:07
 */
@FeignClient(name = "service-usercenter")
public interface ServiceUsercenterClient {

    @PostMapping("/getUserByName")
    @ApiOperation(value = "根据用户名查询用户信息")
    TCostomerServerUser getUserByName(String username);

    @PostMapping("/getRuleByUserId")
    @ApiOperation(value = "根据userID查询用户权限")
    Map<String, List<TCostomerServerRule>> getRuleByUserId(String userId);
    @PostMapping("/registerUserInfo")
    @ApiOperation(value = "根据userID查询用户权限")
    boolean registerUserInfo( TCostomerServerUser userInfor);
}
