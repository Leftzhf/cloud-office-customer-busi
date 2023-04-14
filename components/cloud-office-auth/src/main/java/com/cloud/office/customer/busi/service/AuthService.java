package com.cloud.office.customer.busi.service;

import com.cloud.office.customer.busi.common.exception.user.UserExistsException;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    String login(String username, String password);
    String refreshToken(String oldToken);
    boolean validateToken(HttpServletRequest request);
    void register(RegisterUserDto registerUserDto) throws UserExistsException;
}
