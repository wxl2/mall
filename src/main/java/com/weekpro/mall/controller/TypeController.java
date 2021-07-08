package com.weekpro.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weekpro.mall.entity.GoodsType;
import com.weekpro.mall.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * TODO
 * @ClassName TypeController
 * @Author asuna
 * @Date 2021/7/8 - 9:12
 */
@RestController
public class TypeController {
	@Autowired
	private typeService typeService;
	//显示已有类别
	@GetMapping("/getTypeList")
	public Map<String,Object> getTypeList(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<GoodsType> c_list = typeService.getTypeList();
		if(c_list == null){
			map.put("code","-1");
			map.put("msg","暂无类型");
			map.put("count",0);
			map.put("data","[]");
		}
		else{
			map.put("code","0");
			map.put("msg","ok");
			map.put("count",c_list.size());
			List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
			for(int i = 0;i<c_list.size();i++){
				Map<String,Object> map_ = new HashMap<String,Object>();
				map_.put("id", c_list.get(i).getTypeId());
				map_.put("type",c_list.get(i).getTypeName());
				datalist.add(map_);
			}
			map.put("data",datalist);
		}
		return map;
	}

	//类别管理
	// 删除 action:0 编辑 action:1 增加:2
	@PostMapping("/mangerType")
	public String mangerType(@RequestBody String text){
		JSONObject obj = JSON.parseObject(text);
		int action = obj.getInteger("action");
		if(action == 0){
			if(typeService.typeDelete(obj.getInteger("data")) == 0)
				return "操作成功";
		}else if (action == 1){
			JSONObject data = obj.getJSONObject("data");
			if(typeService.typeUpdate(new GoodsType(data.getInteger("id"),
					data.getString("type"))) == 0)
				return "操作成功";
		}else{
			String typeName = obj.getString("data");
			int typeId = (int)(Math.random()*9000)+1000;
			if(typeService.insertType(new GoodsType(typeId,typeName)) == 0)
				return "操作成功";
		}
		return "操作失败";
	}
}
