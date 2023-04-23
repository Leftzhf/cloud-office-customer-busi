package com.cloud.office.customer.busi.exception.user;

/**
 * 新增用户的时候可能抛出用户已存在异常
 *
 */
public class UserExistsException extends RuntimeException {

    private static final long serialVersionUID = -6257476137002602963L;

    public UserExistsException(String message) {
        super(message);
    }
}
