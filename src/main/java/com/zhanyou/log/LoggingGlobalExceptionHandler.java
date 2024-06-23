package com.zhanyou.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class LoggingGlobalExceptionHandler {

    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        if (e instanceof MissingServletRequestParameterException) {
            log.error("exception happens with request uri is {}, with message {}", request.getRequestURI(), e.getMessage());
        } else {
            log.warn("exception happens with request uri is {}, with message {}", request.getRequestURI(), e.getMessage());
        }
        throw e;
    }

}