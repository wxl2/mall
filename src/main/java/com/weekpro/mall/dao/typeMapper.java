package com.weekpro.mall.dao;

import com.weekpro.mall.entity.GoodsType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface typeMapper {
    //导出表信息
    @Select("select * from goodstype")
    public List<GoodsType> getTypeList();
    //插入一条数据
    @Insert("insert into goodstype (`typeid`, `typename`) values(#{typeId},#{typeName})")
    public void insertType(GoodsType goodsType);
    //删除类别
    @Delete("delete from goodstype where typeid =  #{typeId}")
    public void deleteType(@Param("typeId") int typeId);
    //修改类别名
    @Update("update goodstype set typename=#{typeName} where typeid = #{typeId}")
    public void updateType(GoodsType type);

//    @Select("select * from goodstype where typeid = #{typeid}")
//    public GoodsType getTypeName(@Param("typeid") int typeid);
}