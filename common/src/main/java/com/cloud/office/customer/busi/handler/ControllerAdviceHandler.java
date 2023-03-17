package com.cloud.office.customer.busi.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.cloud.office.customer.busi.enums.ResultCodeEnum;
import com.cloud.office.customer.busi.exception.ApplicationException;
import com.cloud.office.customer.busi.web.ResponseData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统一响应处理器
 * 1 在每个responseBody的响应返回之前进行处理
 * 2 全局异常捕捉 统一返回格式
 **/
@Slf4j
@ControllerAdvice(basePackages = "com.cloud.office.customer.busi")
public class ControllerAdviceHandler implements ResponseBodyAdvice<Object> {

    public static final String ERROR_MSG_404 = "接口地址不存在";
    private static final Integer STATUS_404 = 404;

    /**
     * 决定是否执行beforeBodyWrite()方法
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }


    /**
     * 写入json之前，统一拦截controller返回的数据做封装
     *
     * @param o                  o
     * @param methodParameter    方法参数
     * @param mediaType          媒体类型
     * @param aClass             一个类
     * @param serverHttpRequest  服务器http请求
     * @param serverHttpResponse 服务器http响应
     * @return {@link Object}
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果没有数据返回，则返回失败
        if (o == null) {
            return ResponseData.failure();
        }

        //如果Controller直接返回String的话，会报错，故我们需要手动转换成json。所以,String类型需要特殊处理 手动转为json字符串
//        if (o instanceof String) {
//
//            return JSON.toJSONString(ResponseData.success(o));
//        }
        //如果接口已经做了包装了，这里拦截后就不用再包装
        if (o instanceof ResponseData) {
//            if( ((ResponseData<?>) o).getData() instanceof Boolean)
            return o;
        }
        //如果返回的是布尔值，则根据布尔值进行判断是否成功
        if (o instanceof Boolean) {
            if ((Boolean) o) {
                return ResponseData.success(o);
            } else {
                return ResponseData.failure();
            }
        }
        //404时 返回特定信息
        if (is404(o)) {
            return ResponseData.failure(ResultCodeEnum.ERROR_404.getCode(), ResultCodeEnum.ERROR_404.getMessage());
        }
        return ResponseData.success(o);
    }

    /**
     * 全局异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseData<String> handler(Exception e) {
        //default error message
        String msg = "系统内部出错";
        log.error(msg, e);
        float f[][] = new float[6][6];
        return ResponseData.failure(ResultCodeEnum.SERVICE_ERROR.getCode(), ResultCodeEnum.SERVICE_ERROR.getMessage());
    }


    /**
     * 参数校验异常异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseData<String> handlerConstraintViolationException(Exception e) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
        String msg = StringUtils.collectionToCommaDelimitedString(
                constraintViolationException.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList()));
        return ResponseData.failure(msg);
    }


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData<String> handlerMethodArgumentNotValidException(Exception e) {
        StringBuilder message = new StringBuilder();
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                message.append(StrUtil.toUnderlineCase(fieldError.getField())).append(":").append(fieldError.getDefaultMessage()).append(",");
            } else {
                message.append(objectError.getDefaultMessage()).append(",");
            }

        }
        return ResponseData.failure(message.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResponseData<String> handlerBindException(Exception e) {
        BindException bindException = (BindException) e;
        String msg = StringUtils.collectionToCommaDelimitedString(
                bindException.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
        return ResponseData.failure(msg);
    }

    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseData<String> handlerMissingServletRequestParameterException(Exception e) {
        return ResponseData.failure("缺少必填参数");
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseData<String> handlerHttpMessageNotReadableException(Exception e) {
        return ResponseData.failure("请求参数异常");
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseData<String> handlerIllegalArgumentException(Exception e) {
        return ResponseData.failure("请求参数异常");
    }

    @ResponseBody
    @ExceptionHandler(value = ApplicationException.class)
    public ResponseData<String> handlerParamError(Exception e) {
        log.error(e.getMessage());
        return ResponseData.failure(ResultCodeEnum.SERVICE_ERROR.getCode(), e.getMessage());
    }


    private boolean is404(Object o) {
        if (o instanceof Map) {
            Map<String, Object> map = Convert.toMap(String.class, Object.class, o);
            Integer status = Convert.toInt(map.get("status"));
            return STATUS_404.equals(status);
        }
        return false;
    }
}