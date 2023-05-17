package com.cloud.office.customer.busi.service_im.vo;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.Data;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/17 15:24
 */

@Data
public class ConversationDistributionVO {

    private User Visitor;

    private User Server;

    private Integer conversationId;

}
