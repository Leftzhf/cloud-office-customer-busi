package com.cloud.office.customer.busi.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/18 20:10
 */
public enum UserIdEnum {
    sysUser("-1", "sysUser", "系统用户"),
    anonymous("-2", "anonymous", "匿名用户"),
    unknown("-999", "unknown", "未知用户");

    private String code;
    private String value;
    private String desc;

    private UserIdEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static UserIdEnum getByCode(String code) {
        UserIdEnum[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            UserIdEnum em = var1[var3];
            if (StringUtils.equals(em.getCode(), code)) {
                return em;
            }
        }

        return null;
    }
}