package com.weekpro.mall.controller;

import com.weekpro.mall.entity.applyStore;
import com.weekpro.mall.entity.typeStore;
import com.weekpro.mall.service.adminService;
import com.weekpro.mall.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class typeController {
    @Autowired
    private typeService typeService;

    @GetMapping("/getType")
    public Map<String,Object> getType(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<typeStore> c_list = typeService.getType();
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
                map_.put("type",c_list.get(i).getTypename());
                datalist.add(map_);
            }
            map.put("data",datalist);
        }
        return map;
    }

    // 插入
    @PostMapping("/insertType")
    public void isertType(@RequestBody Map<String,Object> map){
        typeStore typestore = new typeStore(3,23,(String) map.get("typename"));
        typeService.insertType(typestore);
    }


    @PostMapping("/delectType")
    public void delectType(@RequestBody int typeId){
        typeService.typeDelect(typeId);
    }

}
