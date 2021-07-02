package com.weekpro.mall.dao;


import com.weekpro.mall.entity.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper {
    @Select("select * from goods")
    public List<Goods> getGoods();

    // 商家页面显示商家自己的商品
    @Select("select * from goods where username = #{username}")
    public List<Goods> getmMerchantGoods(@Param("username") String username);

    //添加商品
    @Insert("insert into goods (`goodsId`, `goodsName`, `price`, `salesvolume`, `username`, `typename`,`goodsImg`) values(#{goodsId},#{goodsName}),#{price}),#{salesvolume}),#{username}),#{typename}),#{goodsImg})")
    public void addGoods(Goods goods);

    //删除商品
    @Delete("delete from goods where goodsId =  #{goodsId}")
    public void deleteGoods(@Param("goodsId") int goodsId);
}
