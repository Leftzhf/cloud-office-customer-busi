package com.cloud.office.customer.busi.base;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseMongoEntity implements Serializable {

    @Schema(description = "主键id")
    @Id
    private String id;

    @Schema(description = "创建时间")
    private Date gmtCreate;

    @Schema(description = "修改时间")
    private Date gmtModify;

    @Schema(description = "逻辑删除(1:已删除，0:未删除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "备注参数")
    @Transient //被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性
    private Map<String,Object> param = new HashMap<>();
}
