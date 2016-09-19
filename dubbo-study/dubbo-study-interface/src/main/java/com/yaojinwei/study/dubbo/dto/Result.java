package com.yaojinwei.study.dubbo.dto;

public enum Result {
    SUCCESS(1),
    FAILED(0),
    ERROR(-1),
    UPDATEPWD(2);

    private int value;

    private Result(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static boolean isSuccess(Result result) {
        return result == SUCCESS;
    }
}
