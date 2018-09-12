package com.yaojinwei.test.refval;

public class ValueOrRef {
    public static void main(String[] args) {
        Person zhangsan = null;
        changePerson(zhangsan);
        zhangsan.printName();
    }

    public static void changePerson(Person person) {
        person = new Person("LI Si");
    }
}

class Person {
    String name = "default";

    public Person(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    void printName() {
        System.out.println(this.name);
    }
}