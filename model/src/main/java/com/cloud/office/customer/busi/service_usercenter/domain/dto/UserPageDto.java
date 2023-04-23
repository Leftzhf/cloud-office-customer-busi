package com.cloud.office.customer.busi.service_usercenter.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.cloud.office.customer.busi.base.PageDto;
import com.cloud.office.customer.busi.util.EnumValueDeserializer;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.enums.UserStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString(callSuper = true)
public class UserPageDto extends PageDto<User> implements Serializable {

    private static final long serialVersionUID = -4864447250966063233L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 [0.禁用 1.正常 2.删除]
     */
    @JSONField(serializeUsing = EnumValueDeserializer.class, deserializeUsing = EnumValueDeserializer.class)
    private UserStatusEnum status;

    /**
     * 团队编号
     */
    private Integer teamId;
}
