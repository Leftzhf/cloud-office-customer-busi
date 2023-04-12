package com.cloud.office.customer.busi.service_usercenter.domain.dto;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class RoleDto implements Serializable {
    private static final long serialVersionUID = -8858845211780055867L;

    /**
     * 角色信息
     */
    private Role roleInfo;

    /**
     * 角色拥有的权限编号集合
     */
    private List<Integer> permissionIds;
}
