package com.yaojinwei.demo.spring.statemachine.service;

import com.yaojinwei.demo.spring.statemachine.constants.enums.OrderStatus;
import com.yaojinwei.demo.spring.statemachine.constants.enums.OrderStatusChangeEvent;
import com.yaojinwei.demo.spring.statemachine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
 
//    @Autowired
//    private OrderMapper orderMapper;
public static final String stateMachineId = "orderStateMachine";

    @Autowired
    private StateMachineFactory<OrderStatus, OrderStatusChangeEvent> orderStateMachineFactory;
 
//    @Autowired
//    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
 
    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister;
 
    private int id = 1;
    private Map<Integer, Order> orders = new HashMap<>();
 
    @Override
    public Order creat() {
        Order order = new Order();
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        order.setId(id++);
        orders.put(order.getId(), order);
        return order;
    }
 
    @Override
    public Order pay(int id) {
        Order order = orders.get(id);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试支付 id=" + id);
        Message message = MessageBuilder.withPayload(OrderStatusChangeEvent.PAYED).setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 支付失败, 状态异常 id=" + id);
        }
        return orders.get(id);
    }
 
    @Override
    public Order deliver(int id) {
        Order order = orders.get(id);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试发货 id=" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.DELIVERY).setHeader("order", order).build(), orders.get(id))) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 发货失败，状态异常 id=" + id);
        }
        return orders.get(id);
    }
 
    @Override
    public Order receive(int id) {
        Order order = orders.get(id);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试收货 id=" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.RECEIVED).setHeader("order", order).build(), orders.get(id))) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 收货失败，状态异常 id=" + id);
        }
        return orders.get(id);
    }
 
    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }
 
 
//    /**
//     * 发送订单状态转换事件
//     *
//     * @param message
//     * @param order
//     * @return
//     */
//    private synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
//        boolean result = false;
//        try {
//            orderStateMachine.start();
//            //尝试恢复状态机状态
//            persister.restore(orderStateMachine, order);
//            Random random = new Random(100);
//            Thread.sleep(random.nextInt());
//            //添加延迟用于线程安全测试
//            result = orderStateMachine.sendEvent(message);
//            Thread.sleep(random.nextInt());
//            //持久化状态机状态
//            persister.persist(orderStateMachine, order);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            orderStateMachine.stop();
//        }
//        return result;
//    }

    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @param order
     * @return
     */
    private boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
        synchronized (String.valueOf(order.getId()).intern()) {
            boolean result = false;
            StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine = orderStateMachineFactory.getStateMachine(stateMachineId);
            System.out.println("id=" + order.getId() + " 状态机 orderStateMachine" + orderStateMachine);
            try {
                orderStateMachine.start();
                //尝试恢复状态机状态
                persister.restore(orderStateMachine, order);
                System.out.println("id=" + order.getId() + " 状态机 orderStateMachine id=" + orderStateMachine.getId());
                //添加延迟用于线程安全测试
                Thread.sleep(1000);
                result = orderStateMachine.sendEvent(message);
                //持久化状态机状态
                persister.persist(orderStateMachine, order);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                orderStateMachine.stop();
            }
            return result;
        }
    }
}