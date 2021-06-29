package com.weekpro.mall.dao;

import com.weekpro.mall.entity.User;
import com.weekpro.mall.entity.applyStore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author wxl
 * @date 2021/6/28 下午2:47
 * @packageName com.weekpro.mall.dao
 * TODO
 */
public interface applyStoreMapper {
    @Insert("insert into applyStore (`username`, `status`) values(#{username},#{status})")
    public void insertUser(applyStore element);
    @Select("select  username,status from applyStore")
    public List<applyStore> getAllapplyStoreList();
    @Select("select  username,status from applyStore where username=#{username}")
    public applyStore getStoreByUserName(@Param("username") String username);
    @Update("update applyStore set status=#{status} where username=#{username}")
    public void setStatus(@Param("status") String status,@Param("username") String username);
}
