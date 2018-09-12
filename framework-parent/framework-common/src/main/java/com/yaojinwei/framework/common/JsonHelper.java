package com.yaojinwei.framework.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * json工具类，JSON和JAVA的POJO的相互转换
 * @author jinwei.yjw
 * @date 2018/3/23 13:57
 */
public class JsonHelper {
    /**
     * json字符串转为对象List
     * 默认支持三种时间格式："yyyy-MM-dd HH:mm:ss.SSS"、 "yyyy-MM-dd HH:mm:ss"、"yyyy-MM-dd"
     * 可通过JSON.DEFFAULT_DATE_FORMAT指定
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> json2Collection(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * json字符串转为对象
     * 默认支持三种时间格式："yyyy-MM-dd HH:mm:ss.SSS"、 "yyyy-MM-dd HH:mm:ss"、"yyyy-MM-dd"
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * json字符串转为map
     * @param json
     * @return
     */
    public static Map json2Map(String json) {
        return json2Map(json, HashMap.class);
    }

    /**
     * json字符串转为map
     * @param json
     * @return
     */
    public static Map json2Map(String json, Class<? extends Map> clszz) {
        return JSON.parseObject(json, clszz);
    }

    /**
     * 对象数组转为json字符串
     * @param object
     * @return
     */
    public static String collection2json(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 对象数组转为json字符串
     * @param object
     * @param dateFormat 指定时间格式，如： "yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String collection2json(Object object,String dateFormat) {
        return JSON.toJSONStringWithDateFormat(object, dateFormat);
    }

    /**
     * 对象转为json字符串
     * @param object
     * @return
     */
    public static String object2json(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 对象转为json字符串,
     * @param object
     * @param dateFormat 指定时间格式，如： "yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String object2json(Object object,String dateFormat) {
        return JSON.toJSONStringWithDateFormat(object, dateFormat);
    }

}
