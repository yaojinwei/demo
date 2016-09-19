package com.yaojinwei.study.proxy;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/14
 */
public interface Boss {

    /**
     * 审批（各种文件）
     */
    public void approve(String fileName);

    /**
     * 开会
     */
    public void meet(String meetName);

    /**
     * 签发文件
     */
    public void issue(String fileName);

    //工作约会
    //对外招待 //订购票务
}
