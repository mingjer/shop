package net.realme.framework.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.dto.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.dubbo.filter
 *
 * @author 91000044
 * @date 2018/8/25 12:07
 */
public class ValidationExceptionFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(ValidationExceptionFilter.class);

    public ValidationExceptionFilter() {
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;

        try {
            result = invoker.invoke(invocation);
        } catch (RuntimeException var8) {
            logger.error(var8.getMessage(), var8);
            if (GenericService.class != invoker.getInterface() && var8.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException exception = (ConstraintViolationException)var8.getCause();
                ResultT<String> resultVo = new ResultT<>();
                resultVo.setCode(ResultCode.INTERNAL_SERVER_ERROR.getCode());
                ConstraintViolation violation = exception.getConstraintViolations().iterator().next();
                resultVo.setMsg(violation.getPropertyPath() + violation.getMessage());
                resultVo.setData(violation.getPropertyPath().toString());
                return new RpcResult(resultVo);
            }
        }

        return result;
    }
}