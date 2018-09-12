package com.yaojinwei.test;

import java.util.Map;

/**
 * @author jinwei.yjw
 * @date 2018/4/12 17:46
 */
public class TestTryCatch {
    public static void main(String[] args) {
        System.out.println(testFinallyNumber());
    }

    private static int testFinallyNumber(){
        int n =0;
        Map map = null ;
        try {
            try {
                map.put("a", "123");
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("IllegalArgumentException finally");
            }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
            n=1;
            return n;
        }
        finally {
            n=2;
            System.out.println("NullPointerException finnally");
        }
        return n;
    }
}
