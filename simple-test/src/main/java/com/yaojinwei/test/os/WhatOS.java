package com.yaojinwei.test.os;

/**
 * @author jinwei.yjw
 * @date 2018/6/25 10:48
 */
public class WhatOS {
    public static void main(String[] args) {
        System.out.println( System.getProperty("os.name") );
        System.out.println( System.getProperty("os.version") );
        System.out.println( System.getProperty("os.arch") );
    }
}
