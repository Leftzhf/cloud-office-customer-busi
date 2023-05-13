package com.cloud.office.customer.busi.netty.attribute;


import com.cloud.office.customer.busi.service_im.entity.Session;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import io.netty.util.AttributeKey;

import java.util.Map;


public interface Attributes {

    AttributeKey<User> USER_ATTRIBUTE_KEY = AttributeKey.newInstance("user");

    AttributeKey<Map<Integer,Session>> CONVERSATION_ATTRIBUTE_KEY = AttributeKey.newInstance("session");

    AttributeKey<String> SECRET_KEY = AttributeKey.newInstance("secretKey");
}
