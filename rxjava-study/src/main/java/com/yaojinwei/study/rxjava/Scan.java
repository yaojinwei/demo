package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * scan与reduce的区别
 * scan每收到一个元素，计算一次，并发射计算结果
 * reduce不论收到多少个元素，都只计算一次，最后发射一个计算结果，在subscriber.onCompleted()时发射
 * reduce
 * @author Yao.Jinwei
 * @date 2017/2/14 17:27
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Scan {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS).reduce(new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long integer, Long integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Long>() {
            @Override
            public void call(Long integer) {
                System.out.println(integer);
            }
        });

        new Scanner(System.in).nextLine();
    }
}
