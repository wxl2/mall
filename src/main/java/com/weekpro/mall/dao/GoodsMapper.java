package com.weekpro.mall.dao;


import com.weekpro.mall.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {


    // 商家页面显示商家自己的商品
    @Select("select goods.*,goodstype.typename from goods left join goodstype on goods.typeid = goodstype.typeid where goodsuser = #{username}")
    public List<Map<String,Object>> getGoodsUser(@Param("username") String username);

    //查询所有商品
    @Select("select goods.*,goodstype.typename from goods left join goodstype on goods.typeid = goodstype.typeid")
    public List<Map<String,Object>> getGoods();

    //根据商品id查询商品信息
    @Select("select goods.*,goodstype.typename from goods,goodstype where goods.goodsid = #{goodsid} and goods.typeid = goodstype.typeid")
    public Map<String,Object> idGetGoods(int goodsid);

    //添加商品
    @Insert("INSERT INTO `goods`(`goodsid`, `goodsname`, `price`, `typeid`, `imgurl`, `goodsuser`) VALUES (#{goodsid},#{goodsname}," +
            "#{price},#{typeid},#{imgurl},#{goodsuser})")
    public void addGoods(Goods goods);

    //删除商品
    @Delete("delete from goods where goodsid =  #{goodsId}")
    public void deleteGoods(@Param("goodsId") int goodsId);

    //修改商品
    @Update("<script> " +
            "update goods  " +
            "<trim prefix='set' suffixOverrides=','>" +
            "<if test='typeid!=0'>typeid=#{typeid},</if>" +
            "<if test='imgurl!=null'>imgurl=#{imgurl},</if>" +
            "<if test='goodsname!=null'>goodsname=#{goodsname},</if>" +
            "<if test='price!=null'>price=#{price},</if>" +
            "</trim>" +
            "where goodsid=#{goodsid}" +
            "</script>")
    public void updateGoods(@Param("goodsname") String goodsname,@Param("price")float price,
                            @Param("typeid")int typeid,@Param("imgurl") String imgurl,
                            @Param("goodsid")int goodsid);
}
