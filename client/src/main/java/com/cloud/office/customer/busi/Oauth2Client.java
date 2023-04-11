package com.cloud.office.customer.busi;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/7 23:47
 */
@FeignClient("cloud-office-auth")
public interface Oauth2Client {


}
