package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @author Yao.Jinwei
 * @date 2017/2/14 17:28
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Map {
    public static void main(String[] args) {
        Observable.range(1, 10).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "A" + integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
}
