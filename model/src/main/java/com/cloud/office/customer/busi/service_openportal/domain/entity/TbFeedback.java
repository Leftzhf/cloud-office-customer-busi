package com.cloud.office.customer.busi.service_openportal.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "tb_feedback")
@ApiModel(value="Feedback对象", description="")
public class TbFeedback extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "评价分数(1-5)")
    private String score;

    @ApiModelProperty(value = "客服id")
    private Integer serverId;

    @ApiModelProperty(value = "访客id")
    private Integer visitorId;

    @ApiModelProperty(value = "会话id")
    private Integer conversationId;

    @ApiModelProperty(value = "逻辑删除(0: 启用,1: 关闭)")
    private Integer isDelete;


}
