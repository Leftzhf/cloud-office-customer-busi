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
 * <p>
 * 
 * </p>
 *
 * @author leftleft
 * @since 2023-04-03
 */
@Getter
@Setter
@TableName("t_costomer_server_user_role")
@ApiModel(value = "TCostomerServerUserRole对象", description = "")
public class TCostomerServerUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键Id")
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("角色id")
    private String roleId;

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
