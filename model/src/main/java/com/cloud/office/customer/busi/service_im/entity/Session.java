package com.cloud.office.customer.busi.service_im.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/11 14:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private Integer sessionId;

    private Integer visitorId;

    private String visitorKey;

    private Integer serverId;

    private String serverKey;

}
