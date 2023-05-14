package com.cloud.office.customer.busi.service_im.vo;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/13 16:37
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServerToCustomersVO {

    User server;
    List<User> customers;
}
