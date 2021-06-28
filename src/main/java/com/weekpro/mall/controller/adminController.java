package com.weekpro.mall.controller;

import com.weekpro.mall.entity.applyStore;
import com.weekpro.mall.service.adminService;
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
 * @author wxl
 * @date 2021/6/28 下午2:33
 * @packageName com.weekpro.mall.controller
 * TODO
 */
@RestController
public class adminController {
    @Autowired
    private adminService adminService;
    @GetMapping("/applyList")
    public Map<String,Object> getList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        List<applyStore> c_list = adminService.getList();
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无申请");
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
                map_.put("username", c_list.get(i).getUsername());
                String status = c_list.get(i).getStatus();
                if(status.equals("0")){
                    status = "待处理";
                }else if(status.equals("1")){
                    status = "已通过";
                }else{
                    status = "已拒绝";
                }
                map_.put("status", status);
                datalist.add(map_);
            }
            map.put("data",datalist);
        }
        return map;
    }
    @PostMapping("/jugeApplyStore")
    public String jugeApplyStore(@RequestBody Map<String,Object> map){
        String username = (String) map.get("username");
        String status = (String) map.get("action");
        if(adminService.setStatus(username,status) == 0)
            return "操作成功";
        return "操作失败";
    }
}