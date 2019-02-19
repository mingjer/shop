package net.realme.framework.util.dto;

/**
 * @author 91000044
 */
public class ResultUtil {

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    public static Result success() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMsg(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result success(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMsg(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result fail(String message) {
        return new Result()
                .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                .setMsg(message);
    }

    public static Result fail(int resultCode, String message) {
        return new Result(resultCode, message);
    }

    public static Result fail(ResultCode resultCode, String message) {
        return new Result()
                .setCode(resultCode)
                .setMsg(message);
    }
    public static Result fail(ResultCode resultCode, Object data) {
        return new Result()
                .setCode(resultCode)
                .setData(data);
    }

    /**
     * 跳转
     * @param location
     * @return
     */
    public static Result redirect(String location) {
        return new Result()
                .setCode(ResultCode.REDIRECT)
                .setMsg(ResultCode.REDIRECT.getMsg())
                .setData(location);
    }
    /**
     * 未登录
     * @return
     */
    public static Result unauthorized(String location) {
        return new Result()
                .setCode(ResultCode.UNAUTHORIZED)
                .setMsg(ResultCode.UNAUTHORIZED.getMsg())
                .setData(location);
    }
    /**
     * 未授权
     * @return
     */
    public static Result forbidden() {
        return new Result()
                .setCode(ResultCode.FORBIDDEN)
                .setMsg(ResultCode.FORBIDDEN.getMsg());
    }
}
