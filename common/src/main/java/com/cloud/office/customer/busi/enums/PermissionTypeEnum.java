package com.cloud.office.customer.busi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
//@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum PermissionTypeEnum {
    /**
     *
     */
    MENU("menu", "菜单"),
    BUTTON("button", "按钮");

    @EnumValue
    @JsonValue
    private final String value;
    private final String typeName;

    PermissionTypeEnum(String value, String typeName) {
        this.value = value;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
