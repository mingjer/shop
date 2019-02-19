package net.realme.framework.util.exception;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.exception
 *
 * @author 91000044
 * @date 2018/8/25 16:59
 */
public class BaseUncheckException extends RuntimeException {

    protected int code;

    public BaseUncheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseUncheckException(String message) {
        super(message);
    }

    public BaseUncheckException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseUncheckException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
