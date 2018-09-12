package com.yaojinwei.study.reactor.rxjava;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yao.Jinwei
 * @date 2017/9/30 12:03
 */
public class ObservableTest {

    @Test
    public void basicTest(){
        //1、简单的示例，了解运作原理
        Observable<String> observable = Observable.create(sub -> {
            sub.onNext("Hello, world!");
            sub.onCompleted();
        });

        observable.subscribe(new Subscriber<String> () {
            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            public void onCompleted() {
                System.out.println("completed");
            }

            public void onError(Throwable e) {

            }
        });
    }

    @Test
    public void justTest(){
        Observable<String> observable = Observable.just("Hello, world!");
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}