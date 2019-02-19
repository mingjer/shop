package net.realme.framework.web.controller;

import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.web.util.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 91000044
 */
@RestController
public class ErrorController extends BasicErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    public ErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }
    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        logger.error("{}", body);
        HttpStatus status = getStatus(request);
        //统一成Result格式
        return new ResponseEntity<>(ResultUtil.fail("Internal Error").toMap(), status);
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        if (RequestHelper.isAjax(request)) {
            ModelAndView modelAndView = new ModelAndView();
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            HttpStatus status = getStatus(request);
            modelAndView.setStatus(status);
            jsonView.setAttributesMap(ResultUtil.fail(status.getReasonPhrase()).toMap());
            modelAndView.setView(jsonView);
            return modelAndView;
        } else {
            return super.errorHtml(request, response);
        }
    }
}
