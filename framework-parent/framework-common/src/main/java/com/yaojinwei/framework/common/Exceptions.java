package com.yaojinwei.framework.common;

/**
 * 异常类的工具集
 *
 * @author jinwei.yjw
 * @date 2018/4/8 11:38
 */
public class Exceptions {
    /**
     * 判断异常链中是否包含目标类
     *
     * @param e         异常
     * @param destClass 目标类
     * @return
     */
    public static boolean existsInChain(Throwable e, Class<? extends Throwable> destClass) {
        if (e == null || destClass == null) {
            return false;
        }
        while (e != null) {
            if (e.getClass().equals(destClass)) {
                return true;
            }
            e = e.getCause();
        }
        return false;
    }

}
