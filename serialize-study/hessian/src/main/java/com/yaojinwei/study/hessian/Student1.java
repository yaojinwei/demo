package com.yaojinwei.study.hessian;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student1 implements Serializable {
    private String name;
    public static String hobby = "eat";
    transient private String address;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // oos.defaultWriteObject();
        oos.writeObject(name);
        oos.writeObject(address);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // ois.defaultReadObject();
        name = (String) ois.readObject();
        address = (String) ois.readObject();
    }

}