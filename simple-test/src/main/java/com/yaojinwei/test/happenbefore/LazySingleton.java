package com.yaojinwei.test.happenbefore;

import java.util.Random;

public class LazySingleton {
    private int someField;

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("线程" + Thread.currentThread() + "等待赋值...");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread() + "结束等待，开始赋值...");
        this.someField = new Random().nextInt(200) + 1;         // (1)
    }

    public static LazySingleton getInstance() {
        if (instance == null) {                               // (2)
            System.out.println("线程" + Thread.currentThread() + "判断为null，进入抢锁...");
            synchronized (LazySingleton.class) {               // (3)
                System.out.println("线程" + Thread.currentThread() + "抢到锁...");
                if (instance == null) {                       // (4)
                    System.out.println("线程" + Thread.currentThread() + "初始化对象...");
                    instance = new LazySingleton();           // (5)
                }
            }
        }
        return instance;                                      // (6)
    }

    public int getSomeField() {
        System.out.println("线程" + Thread.currentThread() + "返回属性...");
        return this.someField;                                // (7)
    }
}