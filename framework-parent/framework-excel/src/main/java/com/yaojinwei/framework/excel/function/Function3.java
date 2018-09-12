package com.yaojinwei.framework.excel.function;

/**
 * @author jinwei.yjw
 * @date 2018/5/10 16:17
 */
public interface Function3<T1,T2,T3,R> {
    R call(T1 arg1,T2 arg2,T3 arg3);
}
