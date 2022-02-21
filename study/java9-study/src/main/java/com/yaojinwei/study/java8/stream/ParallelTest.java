package com.yaojinwei.study.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 静态代码块中的parallelStream导致的死锁
 * https://www.jianshu.com/p/181d7b07aeb6
 */
public class ParallelTest {


    static {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            data.add("===" + i);
        }
        List<String> collect = data.parallelStream().map(ParallelTest::m).collect(Collectors.toList());
    }

    public static String m(String e) {
        System.out.println(Thread.currentThread() + "====" + e);
        return e + "---";
    }


    public static void main(String[] args) {
        System.out.println("end");
    }
}