package com.yaojinwei.study.java8.trycatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @see <a href="https://oomake.com/question/9680399">使用try-with-resources时，flush()调用是必需的</a>
 * @author jinwei.yjw
 * @date 2019-09-08 20:37
 */
public class TryTest {
    public static void main(String[] args) throws IOException {

        //FileOutputStream 没有flush方法
        //
        try(FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
            FileInputStream fileInputStream = new FileInputStream("test.txt")){
            fileOutputStream.write(1123123123);

        }
    }
}
