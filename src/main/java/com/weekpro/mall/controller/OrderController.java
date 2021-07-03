package com.weekpro.mall.controller;

import com.weekpro.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    //商家获取自己的订单
    @GetMapping("/gerOrderUser")
    public Map<String,Object> gerOrderUser(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        // 获取商家的商品编号

        // 根据商家商品编号查询订单

        //return orderService.gerOrderUser(username);
        return null;
    }
}
