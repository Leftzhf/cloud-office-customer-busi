package com.cloud.office.customer.busi.service;

import com.cloud.office.customer.busi.exception.user.UserExistsException;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface AuthService {

    OAuth2AccessToken login(String username, String password);
    String refreshToken(String oldToken);
//    boolean validateToken(HttpServletRequest request);
    void register(RegisterUserDto registerUserDto) throws UserExistsException;
}
