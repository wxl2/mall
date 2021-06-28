package com.weekpro.mall.service;

import com.weekpro.mall.dao.applyStoreMapper;
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
    private applyStoreMapper applyStore;

    public List<applyStore> getList(){
        try {
            return applyStore.getAllapplyStoreList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int setStatus(String username,String status){
        try {
            applyStore.setStatus(status,username);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}
