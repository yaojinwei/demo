package com.yaojinwei.study.hessian.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yaojinwei.study.hessian.api.HelloHessian;

import java.net.MalformedURLException;

/**
 * Created by QDHL on 2017/7/14.
 */
public class HessianClientTest {
    public static void main(String[] args) {
        String url = "http://localhost:8011/HessianService";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            HelloHessian hello = (HelloHessian) factory.create(
                    HelloHessian.class, url);
            System.out.println(hello.sayHello());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
