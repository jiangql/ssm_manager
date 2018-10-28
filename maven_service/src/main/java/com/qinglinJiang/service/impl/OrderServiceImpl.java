package com.qinglinJiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.qinglinJiang.dao.OrderDao;
import com.qinglinJiang.domain.Order;
import com.qinglinJiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    /**
     * 查询所有订单
     * @return
     */
    public List<Order> findAll(Integer currentPage,Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        return orderDao.findAll();
    }


    /**
     * 根据id查订单
     * @param id
     * @return
     */
    public Order findOrderById(String id) {
        return orderDao.findOrderById(id);
    }
}
