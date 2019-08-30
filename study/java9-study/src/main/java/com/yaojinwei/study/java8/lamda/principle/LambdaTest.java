package com.yaojinwei.study.java8.lamda.principle;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @see <a>https://blog.csdn.net/jiankunking/article/details/79825928</a>
 */
public class LambdaTest {

    static Map<Class, String> map = new LinkedHashMap<>();

    public static void printString(String s, Print<String> print) {
        System.out.println(print.getClass());
        map.put(print.getClass(), "123");
        System.out.println(map.size());
        print.print(s);
    }

    public void sout(String x){
        System.out.println(x);
    }


    public static void soutStatic(String x){
        System.out.println(x);
    }

    public static void main(String[] args) {
        printString("test", (x) -> System.out.println(x));

        printString("test111", LambdaTest::soutStatic);
        printString("test111", LambdaTest::soutStatic);

        LambdaTest lambdaTest = new LambdaTest();

        printString("test112221", lambdaTest::sout);

        printString("test112221", lambdaTest::sout);

    }
}