package com.weekpro.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weekpro.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    //商家获取自己的订单
    @GetMapping("/getOrderStore")
    public Map<String,Object> getOrderStore(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> c_list = orderService.getOrderStore(username);
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无数据");
            map.put("count",0);
            map.put("data","[]");
        }
        else{
            map.put("code","0");
            map.put("msg","ok");
            map.put("count",c_list.size());
            // 解析状态  0-> 未发货  1-> 已发货   2->已收货
            map.put("data",c_list);
        }
        return map;
    }
    // 确认发货
    @PostMapping("/updateOrder")
    public String updateOrder(@RequestBody String orderid){
        JSONObject id = JSON.parseObject(orderid);
        int idid = id.getInteger("orderid");
        int test = orderService.updateOrder(idid);
        if(test==-1){
            return "发货失败";

        }else return "发货成功";

    }
    // 用户获取订单
    @GetMapping("/getOrderUser")
    public Map<String,Object> getOrderUser(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> c_list = orderService.getOrderUser(username);
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无数据");
            map.put("count",0);
            map.put("data","[]");
        }
        else{
            map.put("code","0");
            map.put("msg","ok");
            map.put("count",c_list.size());
            // 解析状态  0-> 未发货  1-> 发货
            map.put("data",c_list);
        }
        return map;
    }
    // 确认收货
    @PostMapping("/updateOrderUser")
    public String updateOrderUser(@RequestBody String orderid){
        JSONObject id = JSON.parseObject(orderid);
        int idid = id.getInteger("orderid");
        int test = orderService.updateOrderUser(idid);
        if(test==-1){
            return "收货失败";
        }else return "已收货";
    }
    @PostMapping("/report")
    public Map<String,Object> getReport(@RequestBody String text){
        Map<String,Object> map = new HashMap<String,Object>();
        String[] date = text.split("~");
        Map<String,Object> res = orderService.getGraphical(date[0].trim(),date[1].trim());
        if (res == null) {
            map.put("code",-1);
            return map;
        }
        map.put("code",0);
        map.put("data",res);
        return  map;
    }
}
