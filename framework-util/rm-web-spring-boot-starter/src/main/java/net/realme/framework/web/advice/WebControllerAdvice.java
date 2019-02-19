package net.realme.framework.web.advice;

import net.realme.framework.i18n.MessageSourceService;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author 91000044
 */
@RestControllerAdvice
public class WebControllerAdvice {

    @Autowired
    protected MessageSourceService messageSourceService;

    private static Logger logger = LoggerFactory.getLogger(WebControllerAdvice.class);

    @ExceptionHandler(value = BusinessException.class)
    protected Result exceptionHandler(BusinessException e) {
        return ResultUtil.fail(e.getCode(), messageSourceService.getMsgByCode(e.getMessage()));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected Result exceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage()).append(";");
        }
        return ResultUtil.fail(strBuilder.substring(0, strBuilder.length() - 1));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected Result exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> resultList = e.getBindingResult().getFieldErrors();
        StringBuilder strBuilder = new StringBuilder();
        for (FieldError error : resultList) {
            strBuilder.append(error.getDefaultMessage()).append(";");
        }
        return ResultUtil.fail(strBuilder.substring(0, strBuilder.length() - 1));
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e){
        logger.error("uncaught exception", e);
        return ResultUtil.fail("Internal Error");
    }

}
