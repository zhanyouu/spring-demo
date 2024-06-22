package com.zhanyou.spring_02;

public class RpcException extends RuntimeException{
    public RpcException(String message) {
        super(message);
    }
}
