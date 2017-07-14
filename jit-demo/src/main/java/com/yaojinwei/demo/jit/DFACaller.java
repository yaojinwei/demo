package com.yaojinwei.demo.jit;

import java.util.concurrent.Callable;

public class DFACaller implements Callable<Double> {

  private final int runs;



  public DFACaller(int runs_) {

      runs = runs_;

  }



  @Override

  public Double call() {

      DirectFieldAccess direct = new DirectFieldAccess();

      double sum = 0;

      for (int i = 0; i < runs; i++) {

          direct.one++;

          sum += direct.one;

      }

      return sum;

  }

}


