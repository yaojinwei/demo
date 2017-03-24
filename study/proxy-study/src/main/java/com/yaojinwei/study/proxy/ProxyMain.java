package com.yaojinwei.study.proxy;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public class ProxyMain {
    public static void main(String[] args) {
        MaYun maYun = new MaYun();
        Assister assister = new Assister(maYun);
        assister.issue("员工中秋福利");
    }
}
