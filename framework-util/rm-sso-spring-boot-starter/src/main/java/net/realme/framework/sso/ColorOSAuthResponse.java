package net.realme.framework.sso;

import java.io.Serializable;

/**
 * 会员鉴权接口返回响应体
 * @author 91000044
 */
public class ColorOSAuthResponse implements Serializable {

    private static final long serialVersionUID = 4895898213286351403L;

    protected boolean success;
    protected Error error = new Error();
    protected ColorOSAuthResponseData data;

    static class Error {
        protected String code;
        protected String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ColorOSAuthResponseData getData() {
        return data;
    }

    public void setData(ColorOSAuthResponseData data) {
        this.data = data;
    }
}
