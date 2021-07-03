package com.weekpro.mall.service;

import com.weekpro.mall.dao.typeMapper;
import com.weekpro.mall.entity.GoodsType;
import com.weekpro.mall.entity.typeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("typeService")
public class typeService {
    @Autowired
    private typeMapper typeMapper_;

    // 导出所有的类别信息
    public List<typeStore> getTypeList(){
        try{
            List<typeStore> list = typeMapper_.getTypeList();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 插入一条类别信息
    public int insertType(typeStore typestore){
        try{
            typeMapper_.insertType(typestore);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    // 删除商品类别
    public int typeDelect(int typeId){
        try{
            typeMapper_.delectType(typeId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    //更改商品类别
    public int typeUpdate(typeStore store){
        try {
            typeMapper_.updateType(store);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    //根据typeid查询类别名称
    public String getTypeName(int typeid){
        try {
            GoodsType goodsType = typeMapper_.getTypeName(typeid);
            return goodsType.getTypeName();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
