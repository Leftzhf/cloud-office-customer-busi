package com.cloud.office.customer.busi;

import com.cloud.office.customer.busi.common.ResponseData;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerRule;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/3 18:07
 */
@FeignClient(name = "service-usercenter")
public interface ServiceUsercenterClient {

    @PostMapping("/user/getUserByName")
    ResponseData<TCostomerServerUser> getUserByName(@RequestParam("username") String username);

    @PostMapping("/user/getRuleByUserId")
    ResponseData<Map<String, List<TCostomerServerRule>>> getRuleByUserId(@RequestParam("userId") String userId);
    @PostMapping("/user/registerUserInfo")
    ResponseData<Integer> registerUserInfo(@RequestBody TCostomerServerUser userInfo);
}
