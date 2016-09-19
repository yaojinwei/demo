package com.yaojinwei.study.dubbo.dto;

import java.io.Serializable;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/8/21
 */
public class ListenerDTO implements Serializable {

    private String name;
    private String contry;
//    private Integer age;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "ListenerDTO{" +
                "name='" + name + '\'' +
                ", contry='" + contry + '\'' +
//                ", age=" + age +
                '}';
    }
}
