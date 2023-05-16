package com.cloud.office.customer.busi.service_im.dto;

import com.cloud.office.customer.busi.base.PageDto;
import com.cloud.office.customer.busi.service_im.entity.Message;
import lombok.Data;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/14 18:55
 */
@Data
public class MessageListPageDTO extends PageDto<Message> {

    /**
     * 访客编号
     */
    private Integer  contactUserId;

}
