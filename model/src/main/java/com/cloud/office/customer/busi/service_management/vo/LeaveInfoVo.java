package com.cloud.office.customer.busi.service_management.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/23 18:36
 */
@Data
@ApiModel(value="Leave VO对象", description="")
public class LeaveInfoVo {

    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phoneNumber;

    @ApiModelProperty(value = "评价时间")
    private Date createAt;
}
