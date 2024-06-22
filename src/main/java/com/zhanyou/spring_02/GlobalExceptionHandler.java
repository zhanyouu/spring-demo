package com.zhanyou.spring_02;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e) {
        if (e instanceof RpcException) {
            return "Rpc调用异常";
        }
        if (e instanceof RuntimeException) {
            return "服务内部异常";
        }
        return null;
    }
}
