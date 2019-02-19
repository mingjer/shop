package net.realme.framework.util.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 91000044
 */
public class Result implements Serializable {
    private int code = 200;
    private String msg;
    private Object data;

    public Result() {}

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", this.code);
        map.put("msg", this.msg);
        map.put("data", this.data);
        return map;
    }
}
