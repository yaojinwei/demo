package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author Yao.Jinwei
 * @date 2017/2/14 17:30
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Flatmap {
    public static void main(String[] args) {
        Observable.range(1, 10).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just("A"+integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
}
