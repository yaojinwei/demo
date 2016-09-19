package com.yaojinwei.study.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class AuthProxyFilter implements CallbackFilter {
    public int accept(Method arg0) {
        if(!"query".equalsIgnoreCase(arg0.getName()))   
            return 0;   
        return 1;   
    }   
  
} 