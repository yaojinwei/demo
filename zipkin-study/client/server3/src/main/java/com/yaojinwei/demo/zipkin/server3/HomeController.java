package com.yaojinwei.demo.zipkin.server3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class HomeController {

    @Autowired
    private OkHttpClient client;

    private Random random = new Random();

    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {

        int sleep = random.nextInt(100);

        TimeUnit.MILLISECONDS.sleep(sleep);

        return " [service3 sleep " + sleep + " ms]" ;

    }

}