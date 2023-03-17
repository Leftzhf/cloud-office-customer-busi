package com.cloud.office.customer.busi.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键id")
    @TableId(type = IdType.ASSIGN_ID)
    @NotNull(message = "主键id不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "备注参数")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}
