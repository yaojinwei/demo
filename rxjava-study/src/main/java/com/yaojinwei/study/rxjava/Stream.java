package com.yaojinwei.study.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yao.Jinwei
 * @date 2017/3/1 16:16
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Stream {


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
        //Observable<Integer> printStream  =
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
//        .window(2, 1)
//        .flatMap(new Func1<Observable<Integer>, Observable<Integer>>() {
//            @Override
//            public Observable<Integer> call(Observable<Integer> integerObservable) {
//                return integerObservable.reduce(new Func2<Integer, Integer, Integer>() {
//                    @Override
//                    public Integer call(Integer integer, Integer integer2) {
//                        return integer + integer2;
//                    }
//                });
//            }
//        })
          .scan(0, new Func2<Integer, Integer, Integer>() {
              @Override
              public Integer call(Integer integer, Integer integer2) {
                  return integer + integer2;
              }
          })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("sub:" + integer);
                    }
                });

//          .share()
//          .onBackpressureDrop();

        //统计主题消费者
//        printStream.subscribe(counterSubject);
//
//        // 打印消费者
//        printStream.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("print:" + integer);
//            }
//        });

        new Scanner(System.in).nextLine();
    }
}
