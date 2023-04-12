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
 * @since 2023-04-11
 */
@Getter
@Setter
@TableName("t_costomer_server_group_user")
@ApiModel(value = "TCostomerServerGroupUser对象", description = "")
public class TCostomerServerGroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String groupId;

    private String userId;

    private String isAdmin;

    private LocalDateTime joinTime;

    private LocalDateTime leaveTime;

    private String tenantId;

    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtModify;
}
