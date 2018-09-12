package com.yaojinwei.study.io.timeout;

import java.net.Socket;

/**
 * @author Yao.Jinwei
 * @date 2017/3/29 19:07
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class KeepaliveClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            socket.setKeepAlive(true);
            while(true && null != socket){
                Thread.sleep(10 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
