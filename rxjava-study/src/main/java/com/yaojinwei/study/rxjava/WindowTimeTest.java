package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每3秒统计一次
 * @author Yao.Jinwei
 * @date 2017/3/1 18:05
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class WindowTimeTest {
    public static void main(String[] args) {
        final Subject<Integer, Integer> writeStream = PublishSubject.create();
        final Observable<Integer> readStream = writeStream.share().onBackpressureBuffer(100);
        final BehaviorSubject<Integer> counterSubject = BehaviorSubject.create(0);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        final AtomicInteger n  = new AtomicInteger(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                writeStream.onNext(n.get());

                System.out.println("counterSubject:" + counterSubject.getValue());
            }
        }, 1L, 1L, TimeUnit.SECONDS);

        readStream.window(3, TimeUnit.SECONDS)
        .flatMap(new Func1<Observable<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Observable<Integer> integerObservable) {
                return integerObservable.reduce(0, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        System.out.println("reduce: " + System.currentTimeMillis());
                        return integer + integer2;
                    }
                });
            }
        })
        .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("subscribe:" + integer);
            }
        });

        new Scanner(System.in).nextLine();
    }
}
