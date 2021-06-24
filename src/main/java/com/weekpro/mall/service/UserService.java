package com.weekpro.mall.service;

import com.weekpro.mall.User;
import com.weekpro.mall.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String login(User user){
        try {
            User userExist = (User) userDao.getUserByUserName(user.getUsername());
            if(userExist!=null){
                if(user.getPasswd().equals(userExist.getPasswd())){
                    return userExist.getRole();
                }else {
                    return "-1";
                }
            }else {
                return "-1";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
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
