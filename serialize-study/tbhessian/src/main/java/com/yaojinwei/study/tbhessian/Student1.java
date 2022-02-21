package com.yaojinwei.study.tbhessian;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student1 implements Serializable {
    private String name;
    public static String hobby = "eat";
    transient private String address;


}