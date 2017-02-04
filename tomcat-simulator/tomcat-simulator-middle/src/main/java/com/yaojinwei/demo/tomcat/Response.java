package com.yaojinwei.demo.tomcat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by yaojinwei on 2016/10/19.
 */
public class Response {
    private OutputStream os;

    public Response(OutputStream os)  {
        this.os = os;
    }
    private void writeHeader() throws IOException {
        os.write("GET /index.html HTTP/1.1\n".getBytes("utf-8"));
        os.write(("Date: Wed, 19 Oct 2016 14:08:06 GMT\n" +
                "Content-Type: text/html\n" +
                "Content-Length: 215\n" +
                "Connection: Keep-Alive\n" +
                "Location: http://www.baidu.com/search/error.html\n" +
                "Server: BWS/1.1\n" +
                "X-UA-Compatible: IE=Edge,chrome=1\n" +
                "BDPAGETYPE: 3\n" +
                "Set-Cookie: BDSVRTM=0; path=/\n\n").getBytes("utf-8"));
    }
    public void write(String str) throws IOException{
//        writeHeader();
        os.write(str.getBytes("utf-8"));
        os.flush();
        os.close();
    }

    public void writeHtmlFile(String path) throws IOException {
        String content = FileUtils.getFileContent(path);
        write(content);
    }

    /**
     * 写任何服务文件出去
     * @param path
     * @throws IOException
     */
    public void writeFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buff = new byte[512];
        int len = 0;
        while((len = fis.read(buff)) != -1){
            os.write(buff,0,len);
        }
        fis.close();
        os.flush();
        os.close();
    }
}
