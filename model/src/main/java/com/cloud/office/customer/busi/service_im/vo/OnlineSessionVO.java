package com.cloud.office.customer.busi.service_im.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/13 14:44
 */
@Data
public class OnlineSessionVO {

    List<ServerToCustomersVO> serverToCustomers;

    Integer countSession;
}
