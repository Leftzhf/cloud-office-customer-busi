package com.cloud.office.customer.busi.service_usercenter.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p
 * 
 * </p>
 *
 * @author leftleft
 * @since 2023-04-03
 */
@Getter
@Setter
@TableName("t_costomer_server_costomer")
@ApiModel(value = "TCostomerServerCostomer对象", description = "")
public class TCostomerServerCostomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("客户姓名")
    private String name;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("客户所在公司")
    private String company;

    @ApiModelProperty("客户职位")
    private String jobTitle;

    @ApiModelProperty("客户通信地址")
    private String address;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtModify;

    @ApiModelProperty("租户id")
    private String tenantId;

    @ApiModelProperty("逻辑删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
}
