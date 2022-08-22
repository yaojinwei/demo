package com.yaojinwei.spring.proxy;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
@Log
public class FooAspect {

    @Pointcut("execution(* com.yaojinwei.spring.proxy.service..*.*(..))")
    public void pointcut() {
    }

    @After(value = "pointcut()")
    public void after() {
        log.info(this.getClass().toString());
        log.info("after login");
    }
}