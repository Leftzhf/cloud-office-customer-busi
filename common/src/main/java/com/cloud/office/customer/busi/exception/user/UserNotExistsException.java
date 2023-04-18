package com.cloud.office.customer.busi.exception.user;

/**
 * 用户不存在异常
 */
public class UserNotExistsException extends RuntimeException {

    private static final long serialVersionUID = -6523935380310578412L;

    public UserNotExistsException(String message) {
        super(message);
    }
}
