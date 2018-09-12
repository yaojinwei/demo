package com.yaojinwei.study.reactor.rxjava;

/**
 * @author Yao.Jinwei
 * @date 2017/9/30 11:18
 */
public  class Observable<T> {

    private OnSubscribe onSubscribe;

    public Observable(OnSubscribe onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    void subscribe(Subscriber<T> sub){
        onSubscribe.call(sub);
    }

    interface OnSubscribe{
        void call(Subscriber sub);
    }

    public static Observable create(OnSubscribe onSubscribe){
        return new Observable(onSubscribe);
    }

    public static Observable just(Object value){
        return new Observable(new OnSubscribe() {
            @Override
            public void call(Subscriber sub) {
                sub.onNext(value);
                sub.onCompleted();
            }
        });
    }
}
