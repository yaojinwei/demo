package com.yaojinwei.test.classloader;

import java.util.concurrent.TimeUnit;

/**
 * 虚拟机会保证一个类<clinit>方法在多线程环境中被正确的加锁、同步，如果由多个线程同时去初始化一个类，
 * 那么只有一个类回去执行<clinit>操作，其他线程会阻塞等待，直到该类<clinit>执行完毕，
 * 而如果一个类的<clinit>方法要做的事情很多，就可能造成多个线程阻塞，在实际应用中这种阻塞是被隐藏的
 * 需要注意的是：虽然其他线程会被阻塞，但如果执行了<clinit>方法那条线程退出<clinit>方法之后，
 * 其他线程唤醒之后也不会在进入<clinit>方法，在同一个类加载器下，一个类型只会被初始化一次
 */
public class Text {

    static class DeadLoopClass

    {
        static
        {
            if (true)
            {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true)
                {
                }

                //try {
                //    TimeUnit.SECONDS.sleep(10);
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //}
            }

        }

    }

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread a = new Thread(runnable);
        Thread b = new Thread(runnable);
        a.start();
        b.start();

    }

}