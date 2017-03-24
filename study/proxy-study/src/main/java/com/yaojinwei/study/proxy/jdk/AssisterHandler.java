package com.yaojinwei.study.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public class AssisterHandler implements InvocationHandler {
    private Object target;
    public AssisterHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("助理做准备工作");
        Object result = null;
        result = method.invoke(target, args);
        System.out.println("助理处理后续事宜");
        return result;
    }
}
