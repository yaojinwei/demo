package com.yaojinwei.study.dubbo;

import com.yaojinwei.study.dubbo.dto.Code;
import com.yaojinwei.study.dubbo.dto.Message;
import com.yaojinwei.study.dubbo.dto.Result;

public class Messages {
    public static <D> Message<D> success(D data) {
        return new Message(Result.SUCCESS, Code.SUCCESS.getValue(), data);
    }

    public static <D> Message<D> success() {
        return success((D)null);
    }

    public static <D> Message<D> failed(int code) {
        return new Message(Result.FAILED, code);
    }

    public static <D> Message<D> failed(int code, String msg) {
        return new Message(Result.FAILED, Integer.valueOf(code), msg);
    }

    public static <D> Message<D> error() {
        return new Message(Result.ERROR, (Object)null);
    }

    public static <D> Message<D> error(int code) {
        return new Message(Result.ERROR, code);
    }

    public static boolean isSuccess(Message<?> message) {
        return Result.SUCCESS.equals(message.getResult());
    }

    private Messages() {
    }
}