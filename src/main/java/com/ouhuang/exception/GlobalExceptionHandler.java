package com.ouhuang.exception;

import com.ouhuang.utils.HttpResp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public HttpResp exceptionHandler(Exception e) {
        e.printStackTrace();
        return HttpResp.fail("服务器出错啦");
    }
}
