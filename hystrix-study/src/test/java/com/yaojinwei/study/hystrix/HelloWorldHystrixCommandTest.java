package com.yaojinwei.study.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.functions.Action1;

import static org.junit.Assert.*;

/**
 * @author Yao.Jinwei
 * @date 2017/12/19 17:00
 * HystrixCommand的observe()与toObservable()的区别：
 * 1）observe()会立即执行HelloWorldHystrixCommand.run()；toObservable()要在toBlocking().single()或subscribe()时才执行HelloWorldHystrixCommand.run()
 * 2）observe()中，toBlocking().single()和subscribe()可以共存；在toObservable()中不行，因为两者都会触发执行HelloWorldHystrixCommand.run()，这违反了同一个HelloWorldHystrixCommand对象只能执行run()一次原则
 * 3）observe()可以重复订阅，底层使用ReplaySubject缓存结果，ReplaySubject是一个可重放的Subject
 * @throws Exception
 */
public class HelloWorldHystrixCommandTest {
    private Logger logger = LoggerFactory.getLogger(HelloWorldHystrixCommandTest.class);

    @org.junit.Test
    public void run() throws Exception {
        new HelloWorldHystrixCommand("HLX").execute();
    }

    @org.junit.Test
    public void toObservable(){
        Observable<String> observable = new HelloWorldHystrixCommand("HLX").toObservable();
        logger.info("observable");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                logger.info(s);
            }
        });
        //第二次会报错
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                logger.info(s);
            }
        });
    }

    @org.junit.Test
    public void observe() throws Exception {
        /* 调用程序对HelloWorldHystrixCommand实例化，执行execute()即触发HelloWorldHystrixCommand.run()的执行 */
        Observable<String> observable = new HelloWorldHystrixCommand("HLX").observe();
        logger.info("observe");
        // single()是堵塞的
        System.out.println("hotObservable single结果：" + observable.toBlocking().single());

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                logger.info(s);
            }
        });
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                logger.info(s);
            }
        });
        System.in.read();
//        logger.info(result);  // 打印出Hello HLX
    }

}