package com.cloud.office.customer.busi.config;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.cloud.office.customer.busi.system.SysUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/17 19:17
 */
public class MybatisConfig implements TenantLineHandler {
    @Override
    public Expression getTenantId() {
        // 返回当前租户ID，例如从ThreadLocal中获取
        String tenantId = SysUtil.getTenantId();
        return new LongValue(tenantId);
    }
    //实现Mybatis-plus多租户插件的配置
    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }
    @Override
    public boolean ignoreTable(String tableName) {
        // 这里可以判断是否过滤表
        return false;
    }
}
