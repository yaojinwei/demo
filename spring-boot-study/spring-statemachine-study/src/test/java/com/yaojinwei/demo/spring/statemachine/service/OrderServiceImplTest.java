package com.yaojinwei.demo.spring.statemachine.service;

import com.yaojinwei.demo.spring.statemachine.Application;
import com.yaojinwei.demo.spring.statemachine.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void testMultThread() throws InterruptedException, ExecutionException {
        List<Future> futureList  = new ArrayList<>();
        for(int i=1;i<=5;i++){
            int j = i;
            Future future = executorService.submit(() -> {
                orderService.creat();
                orderService.pay(j);
            });
            futureList.add(future);
        }
        Thread.sleep(1000);
        for(int i=1;i<=5;i++){
            int j = i;
            Future future = executorService.submit(() -> {
                orderService.deliver(j);
                orderService.receive(j);
            });
            futureList.add(future);
        }
        for(int i=6;i<=10;i++){
            int j = i;
            Future future = executorService.submit(() -> {
                orderService.creat();
                orderService.pay(j);
            });
            futureList.add(future);
        }
        Thread.sleep(1000);
        for(int i=6;i<=10;i++){
            int j = i;
            Future future = executorService.submit(() -> {
                orderService.deliver(j);
                orderService.receive(j);
            });
            futureList.add(future);
        }


//        for(Future future:futureList){
//            future.get();
//        }
        Thread.sleep(10000);
        Map<Integer, Order> orderMap = orderService.getOrders();
        for(Map.Entry entry:orderMap.entrySet()){
            System.out.println(entry.getKey() + " order:" + entry.getValue());
        }


    }
}