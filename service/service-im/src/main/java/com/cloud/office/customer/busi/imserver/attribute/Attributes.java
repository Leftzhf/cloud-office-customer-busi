package com.cloud.office.customer.busi.imserver.attribute;

import com.cloud.office.customer.busi.imserver.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
