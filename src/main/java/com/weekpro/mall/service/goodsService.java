package com.weekpro.mall.service;

import com.weekpro.mall.dao.GoodsMapper;
import com.weekpro.mall.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @version 1.0
 * TODO
 * @ClassName goodsService
 * @Author asuna
 * @Date 2021/7/2 - 10:02
 */
@Service("goodsService")
public class goodsService {
	@Autowired
	private GoodsMapper goodsMapper_;

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
//	public int deleteGoods(int goodsId){
//		try{
//			goodsMapper_.deleteGoods(goodsId);
//		}catch (Exception e){
//			e.printStackTrace();
//			return -1;
//		}
//		return 0;
//	}
}
