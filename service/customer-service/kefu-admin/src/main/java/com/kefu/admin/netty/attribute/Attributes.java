package com.kefu.admin.netty.attribute;

import com.kefu.admin.entity.User;

import io.netty.util.AttributeKey;


public interface Attributes {

    AttributeKey<User> USER_ATTRIBUTE_KEY = AttributeKey.newInstance("user");
}
