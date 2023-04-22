package com.cloud.office.customer.busi.service_openportal.domain.dto;

import com.cloud.office.customer.busi.db.dto.PageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbLeaveInfo;
import lombok.Data;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/22 22:03
 */
@Data
public class LeaveInfoDto extends PageDto<TbLeaveInfo> {

    Integer conversationId;
}
