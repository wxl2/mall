package com.weekpro.mall.entity;

import javax.xml.crypto.Data;

public class Order {
    String orderid;
    int goodsid;
    String username;
    int orderstatus;
    String ordertine;
    public Order(){}

    public Order(String orderid, int goodsid, String username, int orderstatus, String ordertine) {
        this.orderid = orderid;
        this.goodsid = goodsid;
        this.username = username;
        this.orderstatus = orderstatus;
        this.ordertine = ordertine;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrdertine() {
        return ordertine;
    }

    public void setOrdertine(String ordertine) {
        this.ordertine = ordertine;
    }
}
