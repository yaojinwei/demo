package com.yaojinwei.study.nio.client;

import java.util.HashMap;

/**
 * @author Yao.Jinwei
 * @date 2017/3/31 17:22
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HashMapTest {
    public static void main(String[] args) {
        String str[]  = new String[100];
        HashMap map = new HashMap();
        for(int i=0;i<100;i++){
            str[i] = i+"";
            map.put(str[i], str[i]);
        }


    }
}
