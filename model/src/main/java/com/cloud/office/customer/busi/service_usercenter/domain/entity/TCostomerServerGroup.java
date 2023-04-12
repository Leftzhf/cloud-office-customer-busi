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
@TableName("t_costomer_server_group")
@ApiModel(value = "TCostomerServerGroup对象", description = "")
public class TCostomerServerGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String groupId;

    private String groupName;

    private String groupAvatar;

    private Integer groupCreatorId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    private String tenantId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtModify;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
}
