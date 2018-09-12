package com.yaojinwei.framework.common;

import java.lang.reflect.Array;

/**
 * 字符串工具类
 * @author jinwei.yjw
 * @date 2018/3/16 17:13
 */
public class StringUtil {
    /**
     * 判断指定字符串是否为空
     * @param value 字符串
     * @return true：为空  false：不为空
     */
    public static boolean isEmpty(String value){
        return value == null || value.length() == 0;
    }

    /**
     * 判断指定字符串是否不为空
     * @param value 字符串
     * @return true：不为空 false：为空
     */
    public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }

    /**
     * 判断指定多个字符串变量是否全都为空
     * @param args 多个字符串变量
     * @return true：全都为空 false：至少有一个不为空
     */
    public static boolean isAllEmpty(String... args){
        boolean result = true;
        for(String arg:args){
            result &= isEmpty(arg);
        }
        return result;
    }

    /**
     * 判断指定多个字符串变量是否全都不为空
     * @param args 多个字符串变量
     * @return true：全都不为空 false：至少有一个为空
     */
    public static boolean isAllNotEmpty(String... args){
        boolean result = true;
        for(String arg:args){
            result &= isNotEmpty(arg);
        }
        return result;
    }

    public static void main(String[] args) {
        String arg1= "";
        String arg2="";

        System.out.println(isAllEmpty(arg1, arg2));
        Object pojo = new Integer(123);
        int len = Array.getLength(pojo);
        System.out.println(len);

        for (int i = 0; i < len; i++) {
            System.out.println(((Enum<?>) Array.get(pojo, i)).name());;
        }
    }
}