package com.yaojinwei.demo.tomcat;

import java.io.*;

/**
 * 静态资源读取工具类
 * Created by yaojinwei on 2016/10/19.
 */
public class FileUtils {
    public static String getFileContent(String path){
        FileReader reader = null;
        StringBuffer buffer = buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            //字符流
            reader = new FileReader( FileUtils.class.getResource("/").getPath() + path);
            //字符数组处理流
            br = new BufferedReader(reader);
            String line = null;
            while((line  = br.readLine()) != null){
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
