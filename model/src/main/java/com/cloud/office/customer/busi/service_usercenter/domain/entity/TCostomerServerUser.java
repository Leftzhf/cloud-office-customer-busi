//package com.cloud.office.customer.busi.service_usercenter.domain.entity;
//
//import com.baomidou.mybatisplus.annotation.*;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.validation.constraints.NotNull;
//
///**
// * <p>
// *
// * </p>
// *
// * @author leftleft
// * @since 2023-03-17
// */
//@Data
//@TableName("t_costomer_server_user")
//@ApiModel(value = "TCostomerServerUser对象", description = "")
//public class TCostomerServerUser implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @ApiModelProperty("主键id")
//    @TableId(type = IdType.ASSIGN_ID)
//    @NotNull(message = "主键id不能为空")
//    @JsonSerialize(using = ToStringSerializer.class)
//    private String id;
//
//    @ApiModelProperty("用户名称")
//    private String username;
//
//    @ApiModelProperty("用户邮箱")
//    private String email;
//
//    @ApiModelProperty("用户密码")
//    private String password;
//
//    @ApiModelProperty("用户头像url")
//    private String avatarUrl;
//
//    @ApiModelProperty("用户角色")
//    private String role;
//
//    @ApiModelProperty("创建时间")
//    @TableField(fill = FieldFill.INSERT)
//    private LocalDateTime gmtCreate;
//
//    @ApiModelProperty("修改时间")
//    @TableField(fill = FieldFill.INSERT)
//    private LocalDateTime gmtModify;
//
//    @ApiModelProperty("租户id")
//    private String tenantId;
//
//    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
//    @TableLogic
//    @TableField(fill = FieldFill.INSERT)
//    private Integer isDeleted;
//}
