package com.cloud.office.customer.busi.service_openportal.domain.entity;

import com.cloud.office.customer.busi.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2023-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="TbLeaveInfo对象", description="")
public class TbLeaveInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phoneNumber;

    @ApiModelProperty(value = "客服id")
    private Integer serverId;


    @ApiModelProperty(value = "访客id")
    private Integer visitorId;

    @ApiModelProperty(value = "会话id")
    private Integer conversationId;

    @ApiModelProperty(value = "逻辑删除(0: 启用,1: 关闭)")
    private Integer isDelete;


}
