package com.qinglinJiang.controller;

import com.github.pagehelper.PageInfo;
import com.qinglinJiang.domain.Order;
import com.qinglinJiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private ModelAndView mav = new ModelAndView();
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",defaultValue = "1",required = true) Integer currentPage,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize){

        List<Order> orderList = orderService.findAll(currentPage, pageSize);
        PageInfo pageInfo = new PageInfo(orderList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-list");
        return mav;
    }

    @RequestMapping("/findOrderById.do")
    public ModelAndView findOrderById(String id){
        Order order = orderService.findOrderById(id);
        mav.addObject("order",order);
        mav.setViewName("orders-show");
        return mav;
    }
}
