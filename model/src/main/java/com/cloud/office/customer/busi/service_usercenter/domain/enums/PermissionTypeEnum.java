package com.cloud.office.customer.busi.service_usercenter.domain.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.kefu.common.util.EnumValueDeserializer;
import lombok.Getter;


@Getter
@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum PermissionTypeEnum {
    /**
     *
     */
    MENU("menu", "菜单"),
    BUTTON("button", "按钮");

    @EnumValue
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
