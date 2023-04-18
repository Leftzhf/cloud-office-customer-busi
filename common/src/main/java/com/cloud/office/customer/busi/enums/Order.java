package com.cloud.office.customer.busi.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 排序方式
 *
 */
@Getter
//@JSONType(deserializer = EnumValueDeserializer.class, serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
public enum Order implements IEnum {
    /**
     * 按指定列升序排序
     */
    ASC("ASC"),
    /**
     * 按指定列降序排序
     */
    DESC("DESC");

    private String value;

    Order(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
