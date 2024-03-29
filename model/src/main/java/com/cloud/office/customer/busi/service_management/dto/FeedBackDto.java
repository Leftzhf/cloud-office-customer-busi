package com.cloud.office.customer.busi.service_management.dto;

import com.cloud.office.customer.busi.base.PageDto;
import com.cloud.office.customer.busi.service_management.entity.TbFeedback;
import lombok.Data;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/22 21:51
 */
@Data
public class FeedBackDto extends PageDto<TbFeedback> {
    Integer conversationId;
}
