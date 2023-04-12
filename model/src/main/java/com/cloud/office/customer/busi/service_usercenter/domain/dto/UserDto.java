package com.cloud.office.customer.busi.service_usercenter.domain.dto;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -6690577031713839293L;

    /**
     * 用户信息
     */
    private User userInfo;

    /**
     * 角色ID集合
     */
    private List<Integer> roleIds;

    /**
     * 角色英文名称集合。如果有值，则会覆盖roleIds
     */
    private List<String> roleNameEns;
}
