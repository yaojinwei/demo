package com.yaojinwei.demo.spring.statemachine.service;

import com.yaojinwei.demo.spring.statemachine.entity.Order;

import java.util.Map;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface OrderService {
    Order creat();

    Order pay(int id);

    Order deliver(int id);

    Order receive(int id);

    Map<Integer, Order> getOrders();
}
