package com.weekpro.mall.service;

import com.weekpro.mall.dao.GoodsMapper;
import com.weekpro.mall.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
	private GoodsMapper goodsMapper;

	// 添加商品
	public int addGoods(Goods goods){
		try {
			//  生成商品编号，得到用户名字,获取类型Id
			goodsMapper.addGoods(goods);
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	//删除商品
	public int deleteGoods(int goodsId){
		try{
			goodsMapper.deleteGoods(goodsId);
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	//查询全部商品
	public List<Map<String,Object>> getGoods(){
		try{
			List<Map<String,Object>> list = goodsMapper.getGoods();
			return  list;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}	//查询user商品
	public List<Map<String,Object>> getGoodsUser(String username){
		try{
			List<Map<String,Object>> list = goodsMapper.getGoodsUser(username);
			return  list;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public int updateGoods(String goodsname, float price, int typeid,
						  String imgurl, int goodsid){
		try {
			goodsMapper.updateGoods(goodsname,price,typeid,imgurl,goodsid);
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
