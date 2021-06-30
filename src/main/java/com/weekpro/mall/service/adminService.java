package com.weekpro.mall.service;

import com.weekpro.mall.dao.UserDao;
import com.weekpro.mall.dao.applyStoreMapper;
import com.weekpro.mall.dao.typeMapper;
import com.weekpro.mall.entity.applyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxl
 * @date 2021/6/28 下午3:01
 * @packageName com.weekpro.mall.service
 * TODO
 */
@Service("adminService")
public class adminService {
    @Autowired
    private applyStoreMapper applyStore_;
    @Autowired
    private UserDao userDao;

    public List<applyStore> getList(){
        try {
            return applyStore_.getAllapplyStoreList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int setStatus(String username,String status){
        try {
            applyStore_.setStatus(status,username);
            if(status.equals("1"))
                userDao.updateRole("2",username);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int applyToStore(applyStore store){
        try {
            if(applyStore_.getStoreByUserName(store.getUsername()) == null){
                applyStore_.insertUser(store);
                return 0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public applyStore getStatus(String username){
        try{
            applyStore store = applyStore_.getStoreByUserName(username);
            return store;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

