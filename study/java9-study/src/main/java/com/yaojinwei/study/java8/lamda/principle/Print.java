package com.yaojinwei.study.java8.lamda.principle;

@FunctionalInterface
interface Print<T> {
    public void print(T x);
}