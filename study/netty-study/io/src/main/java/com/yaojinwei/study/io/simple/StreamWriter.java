package com.yaojinwei.study.io.simple;

import java.io.*;

/**
 * @author Yao.Jinwei
 * @date 2017/3/22 15:25
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class StreamWriter {

    public static void write(InputStream inStream, OutputStream outputStream) throws IOException {
        byte[] b1 = new byte[8192];

        int len1;
        while((len1 = inStream.read(b1)) > 0) {
            outputStream.write(b1, 0, len1);
        }
//        inStream.close();
//        outputStream.close();
    }

    public static void write0(InputStream inStream, OutputStream outputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inStream);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        int len1;
        while((len1 = bis.read()) > -1) {
            bos.write(len1);
        }
        bos.flush();
//        bis.close();
//        inStream.close();
        bos.close();
//        outputStream.close();
    }

    public static void write1(InputStream inStream, OutputStream outputStream) throws IOException {
        int ch;
        while( (ch=inStream.read()) != -1  ){
            outputStream.write(ch);
        }
//        inStream.close();
        outputStream.close();
    }


    public static void write2(InputStream inStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[inStream.available()];
        inStream.read(buffer);

        outputStream.write(buffer);

//        inStream.close();
//        outputStream.close();
    }
}
