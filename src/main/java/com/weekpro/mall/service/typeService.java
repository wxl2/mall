package com.weekpro.mall.service;

import com.weekpro.mall.dao.typeMapper;
import com.weekpro.mall.entity.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("typeService")
public class typeService {
    @Autowired
    private typeMapper typeMapper_;

    // 导出所有的类别信息
    public List<GoodsType> getTypeList(){
        try{
            List<GoodsType> list = typeMapper_.getTypeList();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 插入一条类别信息
    public int insertType(GoodsType goodsType){
        try{
            typeMapper_.insertType(goodsType);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    // 删除商品类别
    public int typeDelete(int typeId){
        try{
            typeMapper_.deleteType(typeId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    //更改商品类别
    public int typeUpdate(GoodsType type){
        try {
            typeMapper_.updateType(type);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    //根据typeid查询类别名称
//    public String getTypeName(int typeid){
//        try {
//            GoodsType goodsType = typeMapper_.getTypeName(typeid);
//            return goodsType.getTypeName();
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
}
