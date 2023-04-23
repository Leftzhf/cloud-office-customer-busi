package com.cloud.office.customer.busi.service_management.vo;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/23 16:29
 */
@Data
@ApiModel(value="FeedBack VO对象", description="")
public class FeedBackVo implements Serializable {

    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "评价分数(1-5)")
    private String score;

    @ApiModelProperty(value = "服务客服")
    private User server;

    @ApiModelProperty(value = "访客")
    private User visitor;
    @ApiModelProperty(value = "评价时间")
    private Date createAt;

}
