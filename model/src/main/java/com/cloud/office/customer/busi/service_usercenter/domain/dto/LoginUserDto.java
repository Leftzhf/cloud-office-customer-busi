package com.cloud.office.customer.busi.service_usercenter.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
public class LoginUserDto implements Serializable {

    private static final long serialVersionUID = -6055254450736420654L;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 5, max = 18, message = "用户名长度为5-18位")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度为6-18位")
    private String password;

}
