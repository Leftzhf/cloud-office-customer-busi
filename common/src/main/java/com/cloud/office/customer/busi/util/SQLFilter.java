package com.cloud.office.customer.busi.util;

import com.cloud.office.customer.busi.constant.CommonConstants;
import com.cloud.office.customer.busi.exception.common.ApiException;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL过滤
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        // 去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        // 转换成小写
        str = str.toLowerCase();

        // 非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        // 判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.contains(keyword) && !str.equals("update_time")) {
                throw new ApiException(CommonConstants.ERROR_CODE, "包含非法字符");
            }
        }

        return str;
    }
}
