package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

/**
 * @author Yao.Jinwei
 * @date 2017/3/1 18:46
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Interval {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1,TimeUnit.SECONDS).take(10).window(3, TimeUnit.SECONDS).subscribe(new Observer<Observable<Long>>() {
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
