package com.cloud.office.customer.busi.handler;

import com.cloud.office.customer.busi.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/13 11:34
 */
@Slf4j
@RestControllerAdvice("com.cloud.office.customer.busi.controller")
public class GlobalExceptionHandler {

    /**
     * 捕捉其他所有异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultVo globalException(Exception e) {
        log.error("捕捉其他所有异常,{}", e.getMessage());
        return ResultVo.error500(e.getMessage(), e.getCause().toString());
    }

}