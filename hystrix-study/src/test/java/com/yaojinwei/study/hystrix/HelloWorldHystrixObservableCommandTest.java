package com.yaojinwei.study.hystrix;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Action1;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yao.Jinwei
 * @date 2017/12/20 10:05
 */
public class HelloWorldHystrixObservableCommandTest {
    private Logger logger = LoggerFactory.getLogger(HelloWorldHystrixObservableCommandTest.class);
    @Test
    public void construct() throws Exception {
        List<Integer> numbers = new LinkedList<>() ;
        numbers.add(1);
        numbers.add(3);
        numbers.add(5);

        new HelloWorldHystrixObservableCommand(numbers).observe().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                logger.info("onNext: " + integer);
            }
        });
    }

}