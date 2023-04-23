package com.cloud.office.customer.busi.service_usercenter.domain.dto;

import com.cloud.office.customer.busi.base.PageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import lombok.Data;

import java.io.Serializable;


@Data
public class RolePageDto extends PageDto<Role> implements Serializable {
    private static final long serialVersionUID = -5673061265367834313L;

    /**
     * 中文或英文名称
     */
    private String name;

}
