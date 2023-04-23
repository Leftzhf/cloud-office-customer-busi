//package com.cloud.office.customer.busi.enums;
//
//import com.alibaba.fastjson.annotation.JSONType;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.baomidou.mybatisplus.annotation.EnumValue;
//import com.cloud.office.customer.busi.util.EnumValueDeserializer;
//import com.fasterxml.jackson.annotation.JsonValue;
//import lombok.Getter;
//
//@Getter
//@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
//public enum ConversationStatusEnum  {
//    NORMAL(1, "正常"),
//    DELETE(0, "已删除");
//
//    @EnumValue
//    @JsonValue
//    private final Integer value;
//    private final String status;
//
//    ConversationStatusEnum(int value, String status) {
//        this.value = value;
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return String.valueOf(value);
//    }
//}
