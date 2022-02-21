package com.yaojinwei.demo.swagger.markdown;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ResponseMessage<T> {
    private int success;
    private String message;
    private T data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
