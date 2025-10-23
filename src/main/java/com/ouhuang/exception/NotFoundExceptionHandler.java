package com.ouhuang.exception;

import com.ouhuang.utils.HttpResp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public HttpResp exceptionHandler(NoResourceFoundException e) {
        e.printStackTrace();
        return HttpResp.fail("404 Not Found: " + e.getResourcePath());
    }
}
