package com.cloud.office.customer.busi.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.cloud.office.customer.busi.util.EnumValueDeserializer;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum MessageTypeEnum {
    /**
     * 消息类型 [1.文字 2.图片]
     */
    SYSTEM(10000, "系统提示"),
    TEXT(1, "文字"),
    IMAGE(2, "图片");


    @EnumValue
    @JsonValue
    private final int value;
    private final String type;

    MessageTypeEnum(int value, String type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
