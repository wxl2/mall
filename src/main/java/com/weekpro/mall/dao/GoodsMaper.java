package com.weekpro.mall.dao;


import com.weekpro.mall.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMaper {
    @Select("select * from goods")
    public List<Goods> getGoods();

    // 商家页面显示商家自己的商品
    @Select("select * from goods where username = #{username}")
    public List<Goods> getmMerchantGoods(@Param("username") String username);
}
