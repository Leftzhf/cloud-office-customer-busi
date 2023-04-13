package com.cloud.office.customer.busi.service;

import com.cloud.office.customer.busi.common.exception.user.UserExistsException;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;

public interface AuthService {

    String login(String username, String password);
    String refreshToken(String oldToken);
    void register(RegisterUserDto registerUserDto) throws UserExistsException;
}
