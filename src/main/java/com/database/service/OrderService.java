package com.database.service;

import com.database.entity.Order;

import java.util.List;


public interface OrderService {
    List<Order> getOrderList();
    List<Order> getOrderListByUsername(String username);

    List<Order> getOrderListByOrderid(String Orderid);
}
