package com.weekpro.mall.dao;

import com.weekpro.mall.entity.User;
import com.weekpro.mall.entity.typeStore;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface typeMapper {
    /*
    @Select("select * from user where username = #{username}")
    public User getUserByUserName(@Param("username") String username);
    @Update("update user set role=#{status} where username=#{username}")
    public void updateRole(@Param("status") String status,@Param("username") String username);*/
    // 导出表信息
    @Select("select * from typestore")
    public List<typeStore> getType();
    //插入一条数据
    @Insert("insert into typestore (`id`, `typeId`, `typename`) values(#{id},#{typeId},#{typename})")
    public void insertType(typeStore typestore);

    @Delete("delect from typestore where typeId =  #{typeId}")
    public void delectType(@Param("typeId") int typeId);
}
