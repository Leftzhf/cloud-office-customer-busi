package com.cloud.office.customer.busi.service_usercenter.domain.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.kefu.common.util.EnumValueDeserializer;
import lombok.Getter;


@Getter
@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum GenderEnum {
    /**
     * 性别 [0.女 1.男 2.未知]
     */
    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知");

    @EnumValue
    private final int value;
    private final String gender;

    GenderEnum(int value, String gender) {
        this.value = value;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
