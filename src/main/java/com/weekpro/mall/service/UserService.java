package com.weekpro.mall.service;

import com.weekpro.mall.entity.User;
import com.weekpro.mall.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxl
 * @date 2021/6/17 下午6:49
 * @packageName com.weekpro.mall.service
 * TODO
 */
@Service("UserService")
public class UserService {
    @Autowired
    private UserDao userDao;
    public User login(User user){
        Map<String,Object> map_= new HashMap<String,Object>();
        try {
            User userExist = (User) userDao.getUserByUserName(user.getUsername());
            if(userExist!=null){
                if(user.getPasswd().equals(userExist.getPasswd())){
                    return userExist;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int register(User user){
       try {
           userDao.insertUser(user);
           return 0;
       }catch (Exception e){
           e.printStackTrace();
           return -1;
       }
    }
}
