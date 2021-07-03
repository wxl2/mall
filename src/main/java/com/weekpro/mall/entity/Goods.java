package com.weekpro.mall.entity;

public class Goods {

    String goodsname;
    float price;
    String goodsuser;
    int typeid;
    String imgurl;
    int goodsid;

    public Goods(String goodsname, float price, String goodsuser, int typeid, String imgurl, int goodsid) {
        this.goodsname = goodsname;
        this.price = price;
        this.goodsuser = goodsuser;
        this.typeid = typeid;
        this.imgurl = imgurl;
        this.goodsid = goodsid;
    }
    public Goods(){}

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getGoodsuser() {
        return goodsuser;
    }

    public void setGoodsuser(String goodsuser) {
        this.goodsuser = goodsuser;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }
}
