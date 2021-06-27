package com.weekpro.mall.dao;

import com.weekpro.mall.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
