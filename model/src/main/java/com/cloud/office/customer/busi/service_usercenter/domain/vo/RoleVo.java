package com.cloud.office.customer.busi.service_usercenter.domain.vo;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 */
@Data
@NoArgsConstructor
public class RoleVo implements Serializable {
    private static final long serialVersionUID = -3673213007631248072L;

    /**
     * 角色信息
     */
    private Role roleInfo;

    /**
     * 角色拥有的所有权限编号集合
     */
    private List<Integer> allPermissionIds;

    /**
     * 角色拥有的子权限编号集合（不包括父权限编号，为了方便element-ui的tree组件处理）
     */
    private List<Integer> childrenPermissionIds;

    public RoleVo(Role roleInfo, List<Integer> allPermissionIds, List<Integer> childrenPermissionIds) {
        this.roleInfo = roleInfo;
        this.allPermissionIds = allPermissionIds;
        this.childrenPermissionIds = childrenPermissionIds;
    }
}
