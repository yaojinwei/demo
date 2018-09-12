package com.yaojinwei.study.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 要想使用hystrix，只需要继承HystrixCommand或HystrixObservableCommand，简单用法见上面例子。两者主要区别是：
 1、前者的命令逻辑写在run()；后者的命令逻辑写在construct()
 2、前者的run()是由新创建的线程执行；后者的construct()是由调用程序线程执行
 3、前者一个实例只能向调用程序发送（emit）单条数据，比如上面例子中run()只能返回一个String结果；后者一个实例可以顺序发送多条数据，比如demo中顺序调用多个onNext()，便实现了向调用程序发送多条数据，甚至还能发送一个范围的数据集，下面举例说明
 * @author Yao.Jinwei
 * @date 2017/12/19 16:58
 */
public class HelloWorldHystrixCommand extends HystrixCommand<String> {
    private Logger logger = LoggerFactory.getLogger(HelloWorldHystrixCommand.class);
    private final String name;
    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    // 如果继承的是HystrixObservableCommand，要重写Observable construct()
    @Override
    protected String run() {
        logger.info("run thread: " + Thread.currentThread().getName());
        return "Hello " + name;
    }

}
