package com.cloud.office.customer.busi.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 */
@Data
@ToString(callSuper = true)
public class TimeEntity implements Serializable {

    private static final long serialVersionUID = -2336945653652662850L;
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public static final String COL_CREATED_AT = "created_at";

    public static final String COL_UPDATED_AT = "updated_at";

}
