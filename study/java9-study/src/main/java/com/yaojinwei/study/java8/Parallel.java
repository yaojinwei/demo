package com.yaojinwei.study.java8;

import java.util.stream.IntStream;

/**
 * @author Yao.Jinwei
 * @date 2016/12/15 15:00
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Parallel {
    public static void main(String[] args) {
        long t0 = System.nanoTime();

        //初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法

        int a[]= IntStream.range(0, 10_000_000).filter(p -> p % 2==0).toArray();

        long t1 = System.nanoTime();

        //和上面功能一样，这里是用并行流来计算

        int b[]=IntStream.range(0, 10_000_000).parallel().filter(p -> p % 2==0).toArray();

        long t2 = System.nanoTime();

        int c[] = new int[10_000_000];
        int j = 0;
        for(int i=0;i<10_000_000;i++){
            if(i%2 == 0){
                c[j++] = i;
            }
        }
        long t3 = System.nanoTime();

        //我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快

        System.out.printf("serial: %.2fs, parallel %.2fs, tradition %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9, (t3 - t2) * 1e-9);
    }
}
