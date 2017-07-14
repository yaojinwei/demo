package com.yaojinwei.demo.jit;

import java.util.concurrent.Callable;

public class GetSetCaller implements Callable<Double> {

 private final int runs;



 public GetSetCaller(int runs_) {

     runs = runs_;

 }



 @Override

 public Double call() {

     ViaGetSet getSet = new ViaGetSet();

     double sum = 0;

     for (int i = 0; i < runs; i++) {

         getSet.setOne(getSet.getOne() + 1);

         sum += getSet.getOne();

     }

     return sum;

 }

}


