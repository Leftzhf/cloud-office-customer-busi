package com.cloud.office.customer.busi.netty.attribute;


import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import io.netty.util.AttributeKey;


public interface Attributes {

    AttributeKey<User> USER_ATTRIBUTE_KEY = AttributeKey.newInstance("user");
}
