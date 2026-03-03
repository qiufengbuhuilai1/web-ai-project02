package com.itheima.exception;

// 直接继承RuntimeException，无需强制捕获，代码更简洁
public class BusinessException extends RuntimeException {
    // 就用这一个构造方法，完全够用
    public BusinessException(String message) {
        super(message);
    }
}