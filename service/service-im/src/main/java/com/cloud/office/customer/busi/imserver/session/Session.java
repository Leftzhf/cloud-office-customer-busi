package com.cloud.office.customer.busi.imserver.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    // 用户唯一性标识
    private String userId;
    private String userName;

}
