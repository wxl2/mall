package com.weekpro.mall.service;

import com.weekpro.mall.dao.OrderMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("OrderService")
public class OrderService {
    @Autowired
    private OrderMapping orderMapping;

    // 商家获取订单
    public List<Map<String, Object>> getOrderStore(String goodsuser) {
        // 传入商家名字，多表查询
        try {
            return orderMapping.getOrderStore(goodsuser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //确认发货
    public int updateOrder(int orderid) {
        try {
            orderMapping.updateOrder(orderid);
            ;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    // 用户获取订单
    public List<Map<String, Object>> getOrderUser(String username) {
        // 传入用户名字，多表查询
        try {
            List<Map<String, Object>> list = orderMapping.getOrderUser(username);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    //确认发货
    public int updateOrderUser(int orderid) {
        try {
            orderMapping.updateOrderUser(orderid);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
}
