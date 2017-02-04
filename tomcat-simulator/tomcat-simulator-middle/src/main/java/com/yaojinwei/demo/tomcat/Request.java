package com.yaojinwei.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yaojinwei on 2016/10/19.
 */
public class Request {
    //所有的请求都始于请求地址
    private String uri;

    public Request(InputStream is) throws IOException{
        //先定义一个存放字节数组
        byte[] buff = new byte[1024];
        int len = is.read(buff);
        if(len > 0){
            String msg = new String(buff, 0, len);
            System.out.println("================" + msg + "=============");
            //解析请求参数里面的uri
            uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1")-1);
            System.out.println("uri:" + uri + "");
        }
        else{
            System.out.println("bad request!");
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
