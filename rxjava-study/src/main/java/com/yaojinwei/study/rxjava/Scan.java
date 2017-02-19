package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

import java.util.concurrent.TimeUnit;

/**
 * @author Yao.Jinwei
 * @date 2017/2/14 17:27
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Scan {
    public static void main(String[] args) {
        Observable.range(1, 10).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }
}
