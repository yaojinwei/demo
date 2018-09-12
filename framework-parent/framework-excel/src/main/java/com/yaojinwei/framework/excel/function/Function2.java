package com.yaojinwei.framework.excel.function;

/**
 * @author jinwei.yjw
 * @date 2018/5/10 16:10
 */
public interface Function2<T1,T2,R> {
    R call(T1 arg1 ,T2 arg2);
}
