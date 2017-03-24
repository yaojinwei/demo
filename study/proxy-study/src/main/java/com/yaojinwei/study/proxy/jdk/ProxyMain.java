package com.yaojinwei.study.proxy.jdk;

import com.yaojinwei.study.proxy.Boss;
import com.yaojinwei.study.proxy.MaYun;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public class ProxyMain {
    public static void main(String[] args) {
        MaYun maYun = new MaYun();
        ((Boss)(new AssisterFactory(maYun)).getInstance()).meet("G20峰会");

        ProxyUtils.generateClassFile(MaYun.class, "MaYunProxy");

    }
}
