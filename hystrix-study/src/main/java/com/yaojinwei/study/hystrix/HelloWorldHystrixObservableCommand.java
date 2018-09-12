package com.yaojinwei.study.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yao.Jinwei
 * @date 2017/12/19 16:58
 */
public class HelloWorldHystrixObservableCommand extends HystrixObservableCommand<Integer> {
    private Logger logger = LoggerFactory.getLogger(HelloWorldHystrixObservableCommand.class);
    private List<Integer> numbers;

    public HelloWorldHystrixObservableCommand(List<Integer> numbers) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.numbers = numbers;
    }
    // 如果继承的是HystrixObservableCommand，要重写Observable construct()
    @Override
    protected Observable<Integer> construct() {
        logger.info("construct thread: " + Thread.currentThread().getName());
        return Observable.from(numbers);
    }
}
