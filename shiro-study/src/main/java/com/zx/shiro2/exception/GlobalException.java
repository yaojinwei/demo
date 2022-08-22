package com.zx.shiro2.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public String doException(Exception e){
        if(e instanceof AuthorizationException){
            return "error";
        }
        return null;
    }
}
