package net.realme.framework.util.exception;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.exception
 *
 * @author 91000044
 * @date 2018/8/21 13:39
 */
public class ServiceException extends BaseUncheckException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code, String message) {
        super(code, message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(int code, Throwable cause) {
        super(code, cause);
    }
}
