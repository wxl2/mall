package com.weekpro.mall.dao;


import com.weekpro.mall.entity.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {


    // 商家页面显示商家自己的商品
    @Select("select goods.*,goodstype.typename from goods left join goodstype on goods.typeid = goodstype.typeid where goodsuser = #{username}")
    public List<Map<String,Object>> getGoodsUser(@Param("username") String username);

    //查询所有商品
    @Select("select goods.*,goodstype.typename from goods left join goodstype on goods.typeid = goodstype.typeid")
    public List<Map<String,Object>> getGoods();

    //添加商品
    @Insert("INSERT INTO `goods`(`goodsid`, `goodsname`, `price`, `typeid`, `imgurl`, `goodsuser`) VALUES (#{goodsid},#{goodsname}," +
            "#{price},#{typeid},#{imgurl},#{goodsuser})")
    public void addGoods(Goods goods);

    //删除商品
    @Delete("delete from goods where goodsid =  #{goodsId}")
    public void deleteGoods(@Param("goodsId") int goodsId);
}
