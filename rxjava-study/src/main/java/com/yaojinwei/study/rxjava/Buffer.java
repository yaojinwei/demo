package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;

import java.util.List;

/**
 * @author Yao.Jinwei
 * @date 2017/2/14 16:07
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Buffer {
    public static void main(String[] args) {
        Observable.range(1, 10).buffer(4,1).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println(integers);
            }
        });
    }
}
