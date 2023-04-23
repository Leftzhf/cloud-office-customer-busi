package com.cloud.office.customer.busi.service_usercenter.domain.dto;

import com.cloud.office.customer.busi.base.PageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Team;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString(callSuper = true)
public class TeamPageDto extends PageDto<Team> implements Serializable {
    private static final long serialVersionUID = 1462743915029636475L;

    /**
     * 团队/公司名称
     */
    private String name;

}
