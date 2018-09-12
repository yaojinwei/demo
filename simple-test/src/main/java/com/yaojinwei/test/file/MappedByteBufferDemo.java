package com.yaojinwei.test.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 硬盘随机读写和顺序读写测试
 * https://www.cnblogs.com/yangqing/archive/2012/11/13/2767453.html
 * MappedByteBuffer高速缓存文件、RandomAccessFile随机访问
 * https://www.cnblogs.com/zhujiabin/p/5660659.html
 * 总结：

 1、RandomAccessFile是Java输入输出流体系中功能最丰富的文件内容访问类，他提供 了众多的方法来访问文件，它既可以读取文件的内容，也可以说向文件输出数据，本身不带缓冲读写，和FileInputStream、FileOutputStream等一样，直接按字节读写时，性能不可接受；

 2、使用MappedByteBuffer读写，固然性能会得到极大提升；其实只要自己处理缓冲，性能都会有非常大的提升，比如以下两种方式中第一种使用了MappedByteBuffer，第二种自己进行缓冲处理后，对于几兆的文件，后者的效率甚至高于前者，可以从几个size大小看出运行速度，当size较大的时候一次性的读取速度是慢些，但是整体的效率非常之高。

 3、BufferedXXXX之类的缓冲流，如果仅使用默认的buffer size，性能不一定最优，要权衡不同情况各种因素设置大小。
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws Exception {
        /**
         * output: 0.001s(读)
         * input: 0.11s(写)
         * */
        MappedByteBufferTest();
        /**
         * size=1024*8
         * out: 0.0s
         * input: 0.014s
         * */
        /**
         * size=1024*1024*8
         * output: 0.01s
         * input: 0.014s
         * */
        /**
         * size=80
         * output: 0.0s
         * input: 0.546s
         * */
        //BufferTest();
        /**
         * time: 0.585s
         * */
        //BufferedInputStreamTest();
    }

    /*
     * 测试结果与Buffer size有关
     */
    // 1、使用MappedByteBuffer: 0.7s
    public static void MappedByteBufferTest() throws Exception {
        String srcFile = "F:\\Ebook\\偷天.txt";
        String destFile = "F:\\Ebook\\toutian.txt";
        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");
        FileChannel fci = rafi.getChannel();
        FileChannel fco = rafo.getChannel();
        long size = fci.size();
        byte b;
        long start = System.currentTimeMillis();
        MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
        System.out.println("output: " + (double)(System.currentTimeMillis() - start) / 1000 + "s");
        MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            b = mbbi.get(i);
            mbbo.put(i, b);
        }
        fci.close();
        fco.close();
        rafi.close();
        rafo.close();
        System.out.println("input: " + (double)(System.currentTimeMillis() - start) / 1000 + "s");
    }

    // 2、自己处理Buffer(RandomAccessFile): 0.13s
    public static void BufferTest() throws Exception {
        String srcFile = "F:\\Ebook\\偷天.txt";
        String destFile = "F:\\Ebook\\toutian.txt";
        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");

        byte[] buf = new byte[80];
        long start = System.currentTimeMillis();
        int c = rafi.read(buf);
        System.out.println("output: " + (double)(System.currentTimeMillis() - start) / 1000 + "s");
        start = System.currentTimeMillis();
        while (c > 0) {
            if (c == buf.length) {
                rafo.write(buf);
            } else {
                rafo.write(buf, 0, c);
            }

            c = rafi.read(buf);
        }
        System.out.println("input: " + (double)(System.currentTimeMillis() - start) / 1000 + "s");
        rafi.close();
        rafo.close();

    }

    // 3、BufferedInputStream&BufferedOutputStream: 3.02s
    public static void BufferedInputStreamTest() throws Exception {
        String srcFile = "F:\\Ebook\\偷天.txt";
        String destFile = "F:\\Ebook\\toutian.txt";
        FileInputStream rafi = new FileInputStream(srcFile);
        FileOutputStream rafo = new FileOutputStream(destFile);

        BufferedInputStream bis = new BufferedInputStream(rafi, 8192);
        BufferedOutputStream bos = new BufferedOutputStream(rafo, 8192);
        long size = rafi.available();

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            byte b = (byte)bis.read();
            bos.write(b);
        }
        rafi.close();
        rafo.close();
        System.out.println("time: " + (double)(System.currentTimeMillis() - start) / 1000 + "s");

    }
}