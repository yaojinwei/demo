package com.yaojinwei.study.hessian.server;

import com.yaojinwei.study.hessian.api.HelloHessian;

/**
 * Created by QDHL on 2017/7/14.
 */
public class HelloHessianImpl implements HelloHessian {
    public String sayHello() {
        return "server say hello!";
    }
}
