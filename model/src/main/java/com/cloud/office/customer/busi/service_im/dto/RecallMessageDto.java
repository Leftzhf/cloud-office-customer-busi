package com.cloud.office.customer.busi.service_im.dto;

import lombok.Data;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/23 23:29
 */
@Data
public class RecallMessageDto {


    //撤回的消息Id
    private Integer messageId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 联系人用户编号
     */
    private Integer contactUserId;


}
