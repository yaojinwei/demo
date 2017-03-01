package com.yaojinwei.study.rxjava;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable
 * http://blog.csdn.net/axuanqq/article/details/50756530
 * @author Yao.Jinwei
 * @date 2017/2/14 15:49
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Window {

    public static void main(String[] args) throws InterruptedException {
        //有就发射，定义最大发射缓冲数量
        //Observable.range(1,10).window(5, 1).subscribe(new Action1<Observable<Integer>>() {
//        BehaviorSubject conterSubject = BehaviorSubject.create(0);
//        //发射时间间隔
//        Observable.interval(1, TimeUnit.SECONDS).window(3, TimeUnit.SECONDS).flatMap(new Func1<Observable<Long>, Observable<Long>>() {
//            @Override
//            public Observable<Long> call(Observable<Long> integerObservable) {
//                return integerObservable.reduce(0L, new Func2<Long, Long, Long>() {
//                    @Override
//                    public Long call(Long integer, Long integer2) {
//                        return integer + integer2;
//                    }
//                });
//            }
//        }).share().onBackpressureDrop()
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("error");
//                    }
//
//                    @Override
//                    public void onNext(Long integer) {
//                        System.out.println("next" + integer);
//                    }
//                });
//        Thread.sleep(100000);
//        System.out.println(conterSubject.getValue());


//
        Observable.interval(1,TimeUnit.SECONDS).take(10).window(3,TimeUnit.SECONDS).subscribe(new Observer<Observable<Long>>() {
            @Override
            public void onCompleted() {
                System.out.println("------>onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("------>onError()" + e);
            }

            @Override
            public void onNext(Observable<Long> integerObservable) {
                System.out.println("------->onNext()");
                integerObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long integer) {
                        System.out.println("------>call():" + integer);
                    }
                });
            }
        });

        Thread.sleep(10000);
    }

}
