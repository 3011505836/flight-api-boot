package com.fh.exception;

import com.fh.model.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ServerResponse handleException(Exception e) {
        e.printStackTrace();
        return ServerResponse.error();
    }

}
