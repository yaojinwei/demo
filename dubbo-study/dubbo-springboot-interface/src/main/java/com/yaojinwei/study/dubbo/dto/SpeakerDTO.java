package com.yaojinwei.study.dubbo.dto;

import java.io.Serializable;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/8/20
 */
public class SpeakerDTO implements Serializable {

    private String name;
    private String contry;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    @Override
    public String toString() {
        return "SpeakerDTO{" +
                "name='" + name + '\'' +
                ", contry='" + contry + '\'' +
                ", count=" + count +
                '}';
    }
}
