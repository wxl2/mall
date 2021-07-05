package com.weekpro.mall.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface OrderMapping {
    //根据商家名字多表查询
    @Select("select goods.*,order_.orderid,order_.username,order_.orderstatus,order_.ordertime,goodstype.typename " +
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
    //echars数据
    @Select("select sum(goods.price) as totalmoney,goodstype.typename,COUNT(goodstype.typename) as totaltype " +
            "from order_ left join goods on order_.goodsid = goods.goodsid left join goodstype on goods.typeid " +
            "= goodstype.typeid where order_.ordertime BETWEEN  " +
            "#{startTime} and #{endTime}GROUP BY goodstype.typename" )
    public List<Map<String,Object>> selectEcharsData(@Param("startTime") String start,@Param("endTime") String end);
    //查询时间段内的订单
    @Select("SELECT  orderid,goodsid,username,DATE_FORMAT(ordertime,'%Y-%m-%d %H:%i:%S') ordertime FROM order_ WHERE ordertime BETWEEN #{startTime} and #{endTime}")
    public List<Map<String,Object>> selectOrderBytime(@Param("startTime") String start,@Param("endTime") String end);

    @Select("SELECT COUNT(order_.username) FROM order_ WHERE ordertime BETWEEN #{startTime} and #{endTime} GROUP BY order_.username;")
    public List<Map<String,Object>> selectUserCount(@Param("startTime") String start,@Param("endTime") String end);

    @Select(" SELECT sum(goods.price)money from goods,order_ WHERE goods.goodsid = order_.goodsid AND " +
            "ordertime BETWEEN #{startTime} and #{endTime}")
    public Map<String,Object> selectMoneySum(@Param("startTime") String start,@Param("endTime") String end);

}

