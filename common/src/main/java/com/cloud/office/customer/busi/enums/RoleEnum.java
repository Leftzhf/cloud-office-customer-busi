package com.cloud.office.customer.busi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

/**
     * 角色 [1.超级管理员 2.管理员 3.普通用户]
     */

    SUPER_ADMIN(1, "超级管理员"),
    SERVER(3,"普通客服"),
    CUSTOMER(4,"普通访客");

    private Integer level;

    private String name_zh;


}
