package com.yaojinwei.test;

/**
 * @author jinwei.yjw
 * @date 2018/4/26 14:27
 */
public class WhileTest {
    public static void main(String[] args) {
        int read = 0;
        int n=10;
        while((read=n)>0){
            System.out.println(read);
            n --;
        }
    }
}
