package com.yaojinwei.framework.excel.function;

/**
 * @author jinwei.yjw
 * @date 2018/5/10 15:56
 */
public interface Function1<T,R> {
    R call(T arg);
}
