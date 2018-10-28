package com.qinglinJiang.service;

import com.qinglinJiang.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll(Integer currentPage,Integer pageSize);


    Order findOrderById(String id);
}
