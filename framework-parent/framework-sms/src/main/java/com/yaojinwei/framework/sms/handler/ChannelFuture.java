package com.yaojinwei.framework.sms.handler;

import java.util.concurrent.TimeUnit;

/**
 * @author jinwei.yjw
 * @date 2018/4/29 21:46
 */
public interface ChannelFuture {
    Channel getChannel();
    boolean isDone();
    boolean isCancelled();
    boolean isSuccess();
    boolean cancel();
    boolean setSuccess();
    Throwable getCause();

    boolean setFailure(Throwable cause);

    boolean setProgress(long amount, long current, long total);
    void addListener(ChannelFutureListener listener);
    void removeListener(ChannelFutureListener listener);
    ChannelFuture await() throws InterruptedException;
    ChannelFuture awaitUninterruptibly();
    boolean await(long timeout, TimeUnit unit) throws InterruptedException;
    boolean await(long timeoutMillis) throws InterruptedException;

}
