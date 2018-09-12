package com.yaojinwei.test.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://blog.csdn.net/whoamiyang/article/details/53365385
 * 传统IOread文件,不使用缓冲区,用时:4739
 传统IOread文件,使用缓冲区,用时:59
 内存映射文件读取文件,用时:11

 最后,解释一下,为什么使用缓冲区读取文件会比不使用快:
 原因是每次进行IO操作,都要从用户态陷入内核态,由内核把数据从磁盘中读到内核缓冲区,再由内核缓冲区到用户缓冲区,如果没有buffer，读取都需要从用户态到内核态切换，而这种切换很耗时，所以，采用预读，减少IO次数，如果有buffer,根据局部性原理,就会一次多读数据,放到缓冲区中,减少了IO次数.

 * @author jinwei.yjw
 * @date 2018/7/19 20:35
 */
public class MapBufTest {
    public static void main(String[] args) {
        try {
            FileInputStream fis=new FileInputStream("./largeFile.txt");
            int sum=0;
            int n;
            long t1=System.currentTimeMillis();
            try {
                while((n=fis.read())>=0){
                    //  数据处理
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            long t=System.currentTimeMillis()-t1;
            System.out.println("传统IOread文件,不使用缓冲区,用时:"+t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis=new FileInputStream("./largeFile.txt");
            BufferedInputStream bis=new BufferedInputStream(fis);
            int sum=0;
            int n;
            long t1=System.currentTimeMillis();
            try {
                while((n=bis.read())>=0){
                    //  数据处理
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
            long t=System.currentTimeMillis()-t1;
            System.out.println("传统IOread文件,使用缓冲区,用时:"+t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MappedByteBuffer buffer=null;
        try {
            buffer=new RandomAccessFile("./largeFile.txt","rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1253244);
            int sum=0;
            int n;
            long t1=System.currentTimeMillis();
            for(int i=0;i<1024*1024*10;i++){
                //  数据处理
            }
            long t=System.currentTimeMillis()-t1;
            System.out.println("内存映射文件读取文件,用时:"+t);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }

    }
}
