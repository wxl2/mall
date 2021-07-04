package com.weekpro.mall.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface OrderMapping {
    //根据商家名字多表查询
    @Select("select goods.*,order_.orderid,order_.username,order_.orderstatus,order_.ordertine,goodstype.typename " +
            "from goods,order_,goodstype " +
            "where goods.goodsid=order_.goodsid and goods.typeid=goodstype.typeid and goods.goodsuser=#{goodsuser}")
    public List<Map<String,Object>> getOrderStore(@Param("goodsuser") String goodsuser);

    // 商家确认发货
    @Update("update order_ set orderstatus=1 where orderid=#{orderid}")
    public void updateOrder(@Param("orderid") int orderid);

    // 用户获取订单
    @Select("select order_.*,goods.goodsname,goods.price,goods.imgurl,goods.goodsuser,goodstype.typename " +
            "from goods,order_,goodstype " +
            "where order_.username=#{username} and order_.goodsid=goods.goodsid and goods.typeid=goodstype.typeid;")
    public List<Map<String,Object>> getOrderUser(@Param("username") String username);

    //用户收货
    @Update("update order_ set orderstatus=2 where orderid=#{orderid}")
    public void updateOrderUser(@Param("orderid") int orderid);
}

