package com.yaojinwei.study.proxy.jdk;

import com.yaojinwei.study.proxy.Boss;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public class AssisterFactory {
    private Object target;
    public AssisterFactory(Object target){
        this.target = target;
    }

    public Object getInstance(){
        AssisterHandler handler = new AssisterHandler(target);

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
