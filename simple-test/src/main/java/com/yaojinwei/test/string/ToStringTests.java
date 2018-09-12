package com.yaojinwei.test.string;

import com.alibaba.fastjson.JSON;

/**
 * @author jinwei.yjw
 * @date 2018/6/29 19:18
 */
public class ToStringTests {
    private String attr1;
    private Integer attr2;
    private String attr3;

    public ToStringTests(String attr1, Integer attr2, String attr3) {
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
    }

    @Override
    public String toString() {
        return "ToStringTests{" +
            "attr1='" + attr1 + '\'' +
            ", attr2=" + attr2 +
            ", attr3='" + attr3 + '\'' +
            '}';
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long usable = max - total + free;
        System.out.println("最大内存 = " + max);
        System.out.println("已分配内存 = " + total);
        System.out.println("已分配内存中的剩余空间 = " + free);
        System.out.println("最大可用内存 = " + usable);
        long startMem = runtime.totalMemory() - runtime.freeMemory();; // 开始时的内存
        System.out.println("开始时的占用内存 = " + startMem);
        for(int i=0;i<100*1000*1000;i++){
            ToStringTests toStringTests = new ToStringTests("attr1-" + i, i, "attr3-" + i);
            toStringTests.toString();
            JSON.toJSONString(toStringTests);
        }

        long end = System.currentTimeMillis();
        long endMem = runtime.totalMemory() - runtime.freeMemory();; // 结束时的内存
        System.out.println("结束时的占用内存 = " + endMem);
        long orz = endMem - startMem; // 使用内存 现在

        max = runtime.maxMemory();
        total = runtime.totalMemory();
        free = runtime.freeMemory();
        usable = max - total + free;
        System.out.println("----------------------------------------");
        System.out.println("最大内存 = " + max);
        System.out.println("已分配内存 = " + total);
        System.out.println("已分配内存中的剩余空间 = " + free);
        System.out.println("最大可用内存 = " + usable);

        System.out.println("耗时 = " + (end - start) + "ms");
        System.out.println("代码占用的内存 = " + (orz/1024/1024) + "MB");
    }
}


