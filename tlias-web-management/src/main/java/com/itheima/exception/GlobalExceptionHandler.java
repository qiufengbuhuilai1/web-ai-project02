package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error("服务器异常，请稍后再试");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException ex) {
        log.error("异常信息：{}", ex.getMessage());
        String message = ex.getMessage();
        int i = message.lastIndexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String [] arr=errMsg.split(" ");
        String msg="用户名【"+arr[2]+"】已存在";
        return Result.error(msg);
    }
}
