package com.cloud.office.customer.busi.common.util;

import com.cloud.office.customer.busi.common.constant.CommonConstants;
import com.cloud.office.customer.busi.common.exception.common.ApiException;
import com.cloud.office.customer.busi.common.exception.common.ServerException;
import com.cloud.office.customer.busi.common.support.ErrorInfo;

public class ApiUtils {

    public static void error(ErrorInfo errorInfo) {
        error(errorInfo.getCode(), errorInfo.getMessage(), errorInfo.isServerError());
    }

    public static void error(String message) {
        error(CommonConstants.ERROR_CODE, message, false);
    }

    public static void error(Integer code, String message) {
        error(code, message, false);
    }

    public static void error(Integer code, String message, boolean isServerError) {
        if (isServerError) {
            throw new ServerException(code, message);
        }
        throw new ApiException(code, message);
    }
}
