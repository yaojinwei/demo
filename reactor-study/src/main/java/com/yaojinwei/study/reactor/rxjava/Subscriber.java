package com.yaojinwei.study.reactor.rxjava;

/**
 * @author Yao.Jinwei
 * @date 2017/9/30 11:24
 */
public interface Subscriber<T> {
    void onNext(T value);
    void onCompleted();
    void onError(Throwable e);
}
