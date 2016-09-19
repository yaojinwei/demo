package com.yaojinwei.study.proxy;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public class Assister implements Boss {

    private Boss boss;

    public Assister(Boss boss) {
        this.boss = boss;
    }

    public void approve(String fileName) {
        System.out.println("助理代收文件");
        boss.approve(fileName);
        System.out.println("文件传递下一部门");
    }

    public void meet(String meetName) {
        System.out.println("助理起草会议文件");
        boss.meet(meetName);
        System.out.println("助理记录会议纪要");
    }

    public void issue(String fileName) {
        System.out.println("助理起草签发文件");
        boss.issue(fileName);
        System.out.println("文件递交下一部门");
    }
}
