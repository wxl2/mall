package com.weekpro.mall.dao;

import com.weekpro.mall.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author wxl
 * @date 2021/6/16 下午9:44
 * @packageName com.weekpro.mall.dao
 * TODO
 */
public interface UserDao{
    @Insert("insert into user (`username`, `password`, `role`) values(#{username},#{password},#{role})")
    public void insertUser(User user);
    @Select("select * from user where username = #{username}")
    public User getUserByUserName(@Param("username") String username);
    @Update("update user set role=#{status} where username=#{username}")
    public void updateRole(@Param("status") String status,@Param("username") String username);
    @Update("update user set phone=#{phone},addr=#{addr} where username=#{username}")
    public void updatePhoneAddr(@Param("phone") String phone,@Param("addr") String addr,@Param("username") String username);
}
