package com.qinglinJiang.dao;

import com.qinglinJiang.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    List<Order> findAll();

    Order findOrderById(String id);
}
