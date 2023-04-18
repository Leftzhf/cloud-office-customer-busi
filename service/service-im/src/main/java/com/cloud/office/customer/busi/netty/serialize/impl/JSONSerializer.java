package com.cloud.office.customer.busi.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.netty.serialize.Serializer;
import com.cloud.office.customer.busi.netty.serialize.SerializerAlgorithm;

/**
 * json 序列化器
 *
 * @author leftleft
 * @date 2023-04-20
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
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
