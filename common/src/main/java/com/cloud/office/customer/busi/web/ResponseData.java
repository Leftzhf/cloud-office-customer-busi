package com.cloud.office.customer.busi.web;

import com.cloud.office.customer.busi.enums.ResultCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ResponseData", description = "请求统一返回数据")
public class ResponseData<T> {


    @Schema(description = "请求会返回码")
    private Integer code;
    @Schema(description = "状态说明")
    private String codeMsg;
    @Schema(description = "请求返回数据，返回任意类型的对象或数组（对象为空时不返回该字段，数组为空返回0长度的数组）")
    private T data;



    public static <T> ResponseData<T> failure() {
        ResponseData<T> responseData = new ResponseData<>();
       responseData.setCode(ResultCodeEnum.FAIL.getCode());
       responseData.setCodeMsg(ResultCodeEnum.FAIL.getMessage());
        return responseData;
    }

    public static <T> ResponseData<T> success(T o) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(ResultCodeEnum.SUCCESS.getCode());
        responseData.setCodeMsg(ResultCodeEnum.SUCCESS.getMessage());
        responseData.setData(o);

        return responseData;
    }

    public static <T> ResponseData<T> failure(Integer code,String codeMsg) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(code);
        responseData.setCodeMsg(codeMsg);
        return responseData;
    }

    public static <T> ResponseData<T> failure(String codeMsg) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(ResultCodeEnum.INVALITENUM_ERROR.getCode());
        responseData.setCodeMsg(codeMsg);
        return responseData;
    }

}
