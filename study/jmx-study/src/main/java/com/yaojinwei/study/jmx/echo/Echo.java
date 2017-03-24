package com.yaojinwei.study.jmx.echo;

/**
 * @author Yao.Jinwei
 * @date 2016/12/22 18:35
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Echo implements EchoMBean {
    @Override
    public void print(String yourName) {
        System.out.println("Hi " + yourName + "!");
    }
}
