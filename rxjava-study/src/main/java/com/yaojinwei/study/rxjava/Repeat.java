package com.yaojinwei.study.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * rx-java – interval()和repeatWhen()之间的区别,用于从间隔中的Observable进行轮询
 * https://www.jb51.cc/java/126845.html
 *
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class Repeat {
    public static void main(String[] args) {
        //Observable.just(1).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
        //    @Override
        //    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
        //        return objectObservable.delay(5,TimeUnit.SECONDS);
        //    }
        //}).subscribe(new Consumer<Integer>() {
        //    @Override
        //    public void accept(Integer integer) throws Throwable {
        //        Log.d("-----repeat",integer.toString());
        //    }
        //});

        Observable<String> observable = Observable.just("MyString")
            .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
                @Override
                public Observable<?> call(Observable<? extends Void> completed) {
                    System.out.println("call");
                    return completed.delay(1, TimeUnit.SECONDS);
                }
            });
    }
}
