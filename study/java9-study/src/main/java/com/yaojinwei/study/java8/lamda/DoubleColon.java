package com.yaojinwei.study.java8.lamda;

import java.util.SplittableRandom;
import java.util.function.*;

public class DoubleColon {
	
    public static void printStr(String str) {
        System.out.println("printStr : " + str);
    }
	
    public void toUpper(){
        System.out.println("toUpper : " + this.toString());
    }
	
    public void toLower(String str){
        System.out.println("toLower : " + str);
    }

    public int toInt(String str){
        System.out.println("toInt : " + str);
        return 1;
    }

    public String toUpperString(){
        return "toUpper String";
    }

    public static void main(String[] args) {
        Consumer<String> printStrConsumer = DoubleColon::printStr;
        printStrConsumer.accept("printStrConsumer");

        Consumer<DoubleColon> toUpperConsumer = DoubleColon::toUpper;
        toUpperConsumer.accept(new DoubleColon());

        BiConsumer<DoubleColon,String> toLowerConsumer = DoubleColon::toLower;
        toLowerConsumer.accept(new DoubleColon(),"toLowerConsumer");

        BiFunction<DoubleColon,String,Integer> toIntFunction = DoubleColon::toInt;
        int i = toIntFunction.apply(new DoubleColon(),"toInt");

        DoubleColon doubleColon = new DoubleColon();
        Supplier<String> toUppserSupplier = doubleColon::toUpperString;

        System.out.println(toUppserSupplier.get());

        Function<DoubleColon, String> toUpperStringFunction = DoubleColon::toUpperString;



    }
}