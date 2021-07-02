package com.weekpro.mall.entity;

public class Goods {

    String goodsName;
    float price;
    int salesVolume;
    String userName;
    int typeId;
    String typename;
    String goodsImg;
    int goodsId;

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Goods(String goodsName, float price, int salesVolume, String userName, int typeId, String typename, String goodsImg, int goodsId) {
        this.goodsName = goodsName;
        this.price = price;
        this.salesVolume = salesVolume;
        this.userName = userName;
        this.typeId = typeId;
        this.typename = typename;
        this.goodsImg = goodsImg;
        this.goodsId = goodsId;
    }

    public Goods(){}
}
