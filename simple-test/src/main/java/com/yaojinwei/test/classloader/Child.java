package com.yaojinwei.test.classloader;

/**
 * @author jinwei.yjw
 * @date 2018/7/18 17:10
 */
public class Child extends Parent {
    static {
        System.out.println("child static block running...");
    }
}
