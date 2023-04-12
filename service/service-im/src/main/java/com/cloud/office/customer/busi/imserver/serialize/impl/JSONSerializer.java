package com.cloud.office.customer.busi.imserver.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.imserver.serialize.Serializer;
import com.cloud.office.customer.busi.imserver.serialize.SerializerAlogrithm;
import org.springframework.stereotype.Component;

@Component
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
