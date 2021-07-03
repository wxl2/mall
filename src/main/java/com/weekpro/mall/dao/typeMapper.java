package com.weekpro.mall.dao;

import com.weekpro.mall.entity.Goods;
import com.weekpro.mall.entity.GoodsType;
import com.weekpro.mall.entity.typeStore;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface typeMapper {
    // 导出表信息
    @Select("select * from goodstype")
    public List<typeStore> getTypeList();
    //插入一条数据
    @Insert("insert into goodstype (`typeid`, `typename`) values(#{typeId},#{typename})")
    public void insertType(typeStore typestore);
    //删除类别
    @Delete("delete from goodstype where typeid =  #{typeId}")
    public void delectType(@Param("typeId") int typeId);
    //修改类别名和类别id
    @Update("update goodstype set typename=#{typename} where typeid = #{typeId}")
    public void updateType(typeStore type);

    @Select("select * from goodstype where typeid = #{typeid}")
    public GoodsType getTypeName(@Param("typeid") int typeid);
}
