package com.yaojinwei.study.rxjava;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Yao.Jinwei
 * @date 2017/2/14 15:49
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Window {

    public static void main(String[] args) throws InterruptedException {
        //有就发射，定义最大发射缓冲数量
        //Observable.range(1,10).window(5, 1).subscribe(new Action1<Observable<Integer>>() {
        BehaviorSubject conterSubject = BehaviorSubject.create(0);
        //发射时间间隔
        Observable.range(1, 30).window(3, 1).flatMap(new Func1<Observable<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Observable<Integer> integerObservable) {
                return integerObservable.scan(0, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                }).skip(3);
            }
        }).share().onBackpressureDrop()
                .subscribe(conterSubject);
        Thread.sleep(1000);
        System.out.println(conterSubject.getValue());
    }

}
