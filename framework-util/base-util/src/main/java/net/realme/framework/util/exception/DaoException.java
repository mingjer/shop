package net.realme.framework.util.exception;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.exception
 *
 * @author 91000044
 * @date 2018/8/25 16:28
 */
public class DaoException extends BaseUncheckException {

    public DaoException(int code, String message) {
        super(code, message);
    }

    public DaoException(int code, Throwable cause) {
        super(code, cause);
    }
}
