package net.realme.framework.util.dto;

/**
 * @author 91000044
 */

public enum ResultCode {
    SUCCESS(200, "success"),
    NO_CONTENT(204, "no content"),
    REDIRECT(302, "temporary redirect"),
    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    METHOD_NOT_ALLOW(405, "method not allow"),
    REQUEST_TIMEOUT(408, "request timeout"),
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    NOT_IMPLEMENTED(501, "not implement");

    private int code;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCode(int code) {
        this.code = code;
    }

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
