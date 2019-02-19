package net.realme.framework.util.dto;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.dto
 *
 * @author 91000044
 * @date 2018/8/25 11:02
 */
public class ResultT<T> implements Serializable {
    private static final long serialVersionUID = 2970506705031659187L;

    private int code = 200;
    private String msg = null;
    private T data = null;

    public ResultT() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResultT<T> success() {
        ResultT<T> result = new ResultT<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> ResultT<T> success(T t) {
        ResultT<T> result = new ResultT<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(t);
        return result;
    }

    public static <T> ResultT<T> success(T t, String message) {
        ResultT<T> result = new ResultT<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(message);
        result.setData(t);
        return result;
    }

    public static <T> ResultT<T> fail() {
        ResultT<T> result = new ResultT<>();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(ResultCode.INTERNAL_SERVER_ERROR.getMsg());
        return result;
    }

    public static <T> ResultT<T> fail(String msg) {
        ResultT<T> result = new ResultT<>();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultT<T> fail(int status, String msg) {
        ResultT<T> result = new ResultT<>();
        result.setCode(status);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultT<T> fail(ResultCode resultCode) {
        ResultT<T> result = new ResultT<>();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        return result;
    }

    public boolean isSuccess() {
        return this.code == ResultCode.SUCCESS.getCode();
    }

    @Override
    public String toString() {
        return "ResultT{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }
}