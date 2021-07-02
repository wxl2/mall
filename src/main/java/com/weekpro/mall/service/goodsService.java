package com.weekpro.mall.service;

import com.weekpro.mall.dao.GoodsMapper;
import com.weekpro.mall.dao.typeMapper;
import com.weekpro.mall.entity.Goods;
import com.weekpro.mall.entity.typeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("typeService")
public class goodsService {
    @Autowired
    private typeMapper typeMapper_;
    @Autowired
    private GoodsMapper goodsMapper_;

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

    // 添加商品
    public int addGoods(Map<String,Object> map){
        try {
            //  生成商品编号，得到用户名字,获取类型Id
            Goods goods = new Goods((String) map.get("goodsName"),(float) 271,0,"userName",42,(String) map.get("goodsType"),(String) map.get("goodsImg"));
            goodsMapper_.addGoods(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    //删除商品
    public int deleteGoods(int goodsId){
        try{
            goodsMapper_.deleteGoods(goodsId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}
