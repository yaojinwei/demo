package com.yaojinwei.study.hessian;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student implements Serializable {
    private String name;
    public static String hobby = "eat";
    transient private String address;
}