package com.weekpro.mall.service;

import com.weekpro.mall.entity.Order;
import com.weekpro.mall.dao.OrderMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public Map<String,Object> getGraphical(String start,String end){
        Map<String,Object> res = new HashMap<String, Object>();
        try {
            Map<String,Object> top = new HashMap<String, Object>();
            List<Map<String,Object>> echars = orderMapping.selectEcharsData(start,end);
            if(echars == null)
                res.put("echars",0);
            List<Map<String,Object>> buttom = orderMapping.selectOrderBytime(start,end);
            if(buttom == null){
                res.put("buttom",0);
                top.put("ordernum",0);
            }
            List<Map<String,Object>> topCount = orderMapping.selectUserCount(start,end);
            Map<String,Object> moneySum = orderMapping.selectMoneySum(start,end);
            if(topCount == null)
                top.put("orderusernum",0);
            if(moneySum==null)
                top.put("money",0);
            List<Map<String,Object>> top_list = new ArrayList<Map<String, Object>>();
            res.put("echars",echars);
            top.put("ordernum",buttom.size());
            top.put("orderusernum",topCount.size());
            if(moneySum != null)
                top.put("money",moneySum.get("money"));
            top_list.add(top);
            res.put("top",top_list);
            res.put("bottom",buttom);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return res;
    }

    //添加订单
    public int addOrder(int goodsid,String username) {
        try {
            //生成订单编号
            int orderid_ = (int)(Math.random()*100000000);
            String orderid = ""+orderid_;
            //插入数据
            int orderstatus = 0;
            orderMapping.addOrder(orderid,goodsid,username,orderstatus);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;

    }
}
