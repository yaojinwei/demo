package com.yaojinwei.study.hessian;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student2 implements Externalizable {
    private String name;
    public static String hobby = "eat";
    transient private String address;

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(address);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        address = (String) in.readObject();
    }
}