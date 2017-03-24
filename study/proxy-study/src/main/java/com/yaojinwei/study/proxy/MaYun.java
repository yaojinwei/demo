package com.yaojinwei.study.proxy;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public class MaYun implements Boss {

    @Override
    public void approve(String fileName) {
        System.out.println("马云审批文件：" + fileName);
    }

    @Override
    public void meet(String meetName) {
        System.out.println("马云参会：" + meetName);
    }

    @Override
    public void issue(String fileName) {
        System.out.println("马云签发文件：" + fileName);
    }
}
