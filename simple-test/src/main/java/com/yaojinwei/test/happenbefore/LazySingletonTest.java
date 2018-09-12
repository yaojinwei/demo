package com.yaojinwei.test.happenbefore;

import java.util.Random;

/**
 * https://blog.csdn.net/ns_code/article/details/17348313
 * 测试双检锁的单例因HB的问题，导致数据不一致的问题。
 * 按照上面URL的文章所说，线程B应该在A线程初始化someField值之前返回，所以线程B获取不到someField的值
 * 但结果是A线程在没有执行完构造函数时，B线程instance仍然是一个null，从而导致因为抢锁而阻塞
 * 所以最终并不会出现数据不一致的问题
 * @author jinwei.yjw
 * @date 2018/4/26 15:15
 *
 */
public class LazySingletonTest {
    public static void main(String[] args) {
        new Thread(() -> {
            int result = LazySingleton.getInstance().getSomeField();
            System.out.println("A线程：" + result);
        }, "A").start();
        System.out.println("等待1秒后启动B线程");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            int result = LazySingleton.getInstance().getSomeField();
            System.out.println("B线程：" + result);
        }, "B").start();
    }
}
