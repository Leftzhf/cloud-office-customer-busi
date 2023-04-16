package com.cloud.office.customer.busi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
//@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum UserStatusEnum {
    /**
     * 状态 [0.禁用 1.正常 2.已删除]
     */
    DISABLE(0, "禁用"),
    NORMAL(1, "正常"),
    DELETE(2, "已删除");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String status;

    UserStatusEnum(int value, String status) {
        this.value = value;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
