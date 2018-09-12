package com.yaojinwei.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java NIO3：通道和文件通道
 * https://www.cnblogs.com/szlbm/p/5513155.html
 *
 * @author jinwei.yjw
 * @date 2018/7/19 21:09
 */
public class FileChannelTest {

    public static void main(String[] args) {
        try {
            FileChannelTest.testRead();
            FileChannelTest.testWrite();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void testWrite() throws IOException {
        File file = new File("D:/files/writechannel.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fc = raf.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(10);
        String str = "abcdefghij";
        bb.put(str.getBytes());
        bb.flip();
        fc.write(bb);
        bb.clear();
        fc.close();
    }

    private static void testRead() throws IOException {
        File file = new File("D:/files/readchannel.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(35);
        fc.read(bb);
        bb.flip();
        while (bb.hasRemaining())
        {
            System.out.print((char)bb.get());
        }
        bb.clear();
        fc.close();
    }
}
