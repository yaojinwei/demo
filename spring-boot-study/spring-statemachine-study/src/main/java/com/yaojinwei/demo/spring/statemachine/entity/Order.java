package com.yaojinwei.demo.spring.statemachine.entity;

import com.yaojinwei.demo.spring.statemachine.constants.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Getter
@Setter
public class Order {
    private Integer id;
    private OrderStatus status;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
