package com.xiangshang.xsclub.web.advice;

import com.xiangshang.xsclub.web.auth.InvalidAuthException;
import com.xiangshang.xsclub.web.result.Result;
import com.xiangshang.xsclub.web.result.ResultCode;
import com.xiangshang.xsclub.web.result.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ResponseExceptionControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionControllerAdvice.class);

    @ExceptionHandler
    @ResponseBody
    public Result exceptionHandler(ResultException e) {
        return new Result(e.getCode(), e.getMsg());
    }

    @ExceptionHandler
    @ResponseBody
    public Result exceptionHandler(InvalidAuthException e) {
        return new Result(ResultCode.AUTH_ERROR.getCode(), ResultCode.AUTH_ERROR.getMsg());
    }

    @ExceptionHandler
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        LOGGER.error("系统繁忙，请稍候再试", e);
        return new Result(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMsg());
    }
}
