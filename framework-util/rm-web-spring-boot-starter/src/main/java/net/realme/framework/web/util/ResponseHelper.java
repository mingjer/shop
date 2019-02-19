package net.realme.framework.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.common
 *
 * @author 91000044
 * @date 2018/8/8 11:30
 */
public class ResponseHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 手动响应一个json字符串
     * @param response
     * @param data
     */
    public static void jsonResponse(HttpServletRequest request, HttpServletResponse response, Object data) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = response.getWriter();
        String json = toJsonString(data);
        out.write(json);
        out.flush();
    }

    /**
     * 利用jackson转成json字符串
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
