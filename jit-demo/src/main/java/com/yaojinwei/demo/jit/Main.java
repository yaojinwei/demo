package com.yaojinwei.demo.jit;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;

public class Main {

    private static double timeTestRun(String desc, int runs, Callable<Double> callable) throws Exception {

        long start = System.nanoTime();

        callable.call();

        long time = System.nanoTime() - start;

        return (double) time / runs;

    }


    // Housekeeping method to provide nice uptime values for us

    private static long uptime() {

        return ManagementFactory.getRuntimeMXBean().getUptime() + 15;

        // fudge factor

    }


    public static void main(String... args) throws Exception {

        int iterations = 0;

        for (int i : new int[]

                {100, 1000, 5000, 9000, 10000, 11000, 13000, 20000, 100000}) {

            final int runs = i - iterations;

            iterations += runs;


            // NOTE: We return double (sum of values) from our test cases to

            // prevent aggressive JIT compilation from eliminating the loop in

            // unrealistic ways

            Callable<Double> directCall = new DFACaller(runs);

            Callable<Double> viaGetSet = new GetSetCaller(runs);


            double time1 = timeTestRun("public fields", runs, directCall);

            double time2 = timeTestRun("getter/setter fields", runs, viaGetSet);


            System.out.printf("%7d %,7d\t\tfield access=%.1f ns, getter/setter=%.1f ns%n",

                    uptime(), iterations, time1, time2);

            // added to improve readability of the output

            Thread.sleep(100);

        }

    }

}
