package com.cloud.office.customer.busi.netty.serialize;


import com.cloud.office.customer.busi.netty.serialize.impl.JSONSerializer;

/**
 * 序列化接口
 *
 * @author leftleft
 * @date 2023-04-20
 */
public interface Serializer {

    /**
     * 默认序列化器
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
