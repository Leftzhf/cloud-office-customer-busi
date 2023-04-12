package com.cloud.office.customer.busi.exception;

import com.cloud.office.customer.busi.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

    /**
     * 异常对应的返回码
     */
    private Integer code= ResultCodeEnum.SERVICE_ERROR.getCode();
    /**
     * 异常对应的描述信息
     */
    private String msg;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
        this.msg = message;
    }


    public ApplicationException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }



}