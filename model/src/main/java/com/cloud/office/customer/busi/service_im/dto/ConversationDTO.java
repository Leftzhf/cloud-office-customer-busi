package com.cloud.office.customer.busi.service_im.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/19 10:00
 */
@Data
@ToString(callSuper = true)
public class ConversationDTO implements Serializable {

    private static final long serialVersionUID = -2621292550543059724L;
    private Integer teamID;
    /**
     * 发送方用户名
     */
    private String fromUserName;
    /**
     * 发送方用户编号
     */
    private Integer fromUserId;

    /**
     * 接收方用户编号
     */
    private Integer toUserId;

}
