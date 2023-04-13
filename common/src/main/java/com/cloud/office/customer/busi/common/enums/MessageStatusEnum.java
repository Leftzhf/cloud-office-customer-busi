package com.cloud.office.customer.busi.common.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.cloud.office.customer.busi.common.util.EnumValueDeserializer;
import lombok.Getter;

@Getter
@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum MessageStatusEnum {
    /**
     * 消息状态 [1.未读 2.已读]
     */
    UNREAD(1, "未读"),
    READ(2, "已读");

    @EnumValue
    private final int value;
    private final String status;

    MessageStatusEnum(int value, String status) {
        this.value = value;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
