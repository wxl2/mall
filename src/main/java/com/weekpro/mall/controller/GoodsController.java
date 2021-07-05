package com.weekpro.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weekpro.mall.entity.Goods;
import com.weekpro.mall.entity.typeStore;
import com.weekpro.mall.service.goodsService;
import com.weekpro.mall.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {
    @Autowired
    private typeService typeService;
    @Autowired
    private goodsService goodsService;

    @GetMapping("/getTypeList")
    public Map<String,Object> getTypeList(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<typeStore> c_list = typeService.getTypeList();
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
    // 删除 action:0 编辑 action:1 增加:2
    @PostMapping("/mangerType")
    public String mangerType(@RequestBody String text){
        JSONObject obj = JSON.parseObject(text);
        int action = obj.getInteger("action");
        if(action == 0){
            if(typeService.typeDelect(obj.getInteger("data")) == 0)
                return "操作成功";
        }else if (action == 1){
            JSONObject data = obj.getJSONObject("data");
            if(typeService.typeUpdate(new typeStore(data.getInteger("id"),
                    data.getString("type"))) == 0)
                return "操作成功";
        }else{
            String typeName = obj.getString("data");
            int typeId = (int)(Math.random()*9+1)*1000;
            if(typeService.insertType(new typeStore(typeId,typeName)) == 0)
                return "操作成功";
        }
        return "操作失败";
    }
    // 添加商品
    @PostMapping("/addGoods")
    public Map<String,Object> addGoods(@RequestParam("file") MultipartFile file, @RequestParam("params")String params,
                                       HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        JSONObject obj = JSON.parseObject(params);
        if(file == null){
            map.put("code",-1);
            return map;
        }
        if(obj.getString("storename").isEmpty()){
            map.put("code",-1);
            map.put("msg","请输入商品名");
            return map;
        }
        if(obj.getString("storeprice").isEmpty()){
            map.put("code",-1);
            map.put("msg","请输入商品价格");
            return map;
        }
        if(obj.getString("storeprovid").isEmpty()){
            map.put("code",-1);
            map.put("msg","请输入商品分类");
            return map;
        }
        try{
            Date now = new Date();
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
            String path = ResourceUtils.getURL("classpath:").getPath()+"static/images/goods";
            String fileName = f.format(now)+file.getOriginalFilename();
            String filePath = path+'/'+fileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            int goodsid = (int)(Math.random()*100000);
            goodsService.addGoods(new Goods(obj.getString("storename"),obj.getFloat("storeprice")
            ,(String) request.getSession().getAttribute("username"),obj.getInteger("storeprovid"),
                    fileName,goodsid));
            map.put("code",0);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",-1);
        }
        return map;
    }
    //删除商品deleteGoods
    @PostMapping("/deleteGoods")
    public String deleteGoods(@RequestBody String text){
        JSONObject obj = JSON.parseObject(text);

        if(goodsService.deleteGoods(obj.getInteger("goodsid")) == 0)
            return "删除成功";
        return "删除失败";
    }
    // 查询商家商品
    @GetMapping("/getGoodsUser")
    public Map<String,Object> getGoodsUser(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> c_list = goodsService.getGoodsUser(username);
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无数据");
            map.put("count",0);
            map.put("data","[]");
        }
        else{
            map.put("code","0");
            map.put("msg","ok");
            map.put("count",c_list.size());
            map.put("data",c_list);
        }
        return map;
    }
    @GetMapping("/getGoods")
    public Map<String,Object> getGoods(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> c_list = goodsService.getGoods();
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无数据");
            map.put("count",0);
            map.put("data","[]");
        }
        else{
            map.put("code","0");
            map.put("msg","ok");
            map.put("count",c_list.size());
            map.put("data",c_list);
        }
        return map;
    }
    @PostMapping("/changeGoods")
    public String changeGoods(@RequestParam(value ="file",required = false) MultipartFile file, @RequestParam("params")String params) {
        JSONObject obj = JSON.parseObject(params);
        String imgUrl = obj.getString("imgurl");
        if(((!imgUrl.isEmpty())&&file==null)||
                file!=null&&imgUrl.isEmpty())
            return "操作失败";
        if(file!=null){
            try{
                Date now = new Date();
                SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
                String path = ResourceUtils.getURL("classpath:").getPath()+"static/images/goods";
                String fileName = f.format(now)+file.getOriginalFilename();
                imgUrl = fileName;
                String filePath = path+'/'+fileName;
                File dest = new File(filePath);
                file.transferTo(dest);
                File rmfile = new File(path+'/'+obj.getString("imgurl"));
                if(rmfile.exists())
                    rmfile.delete();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(imgUrl.isEmpty())
            imgUrl=null;
        int typeid = 0;
        String typeid_ = obj.getString("typeid");
        if(!typeid_.isEmpty())
            typeid = obj.getInteger("typeid");
        if(goodsService.updateGoods(obj.getString("goodsname"),obj.getFloat("price"),
                typeid,imgUrl,obj.getInteger("goodsid"))==0){
            return "操作成功";
        }
        return "操作失败";
    }

    //商品展示
    @ResponseBody
    @RequestMapping(value="/getProductPage")
    public Map<String, Object> getProductPage(@RequestParam("page")Integer page,
                                              @RequestParam("limit")Integer limit){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> c_list = goodsService.getGoods();
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无数据");
            map.put("count",0);
            map.put("data","[]");
        }
        else{
            map.put("code","0");
            map.put("msg","ok");
            map.put("count",c_list.size());
            map.put("data",c_list);
        }
        return map;
    }
    // 通过商品id获取商品信息
    @PostMapping("/idGetGoods")
    public Map<String,Object> idGetGoods(@RequestBody String goodsid){
        JSONObject obj = JSON.parseObject(goodsid);
        int test = obj.getInteger("goodsid");

        Map<String,Object> c_list = goodsService.idGetGoods(test);
        Map<String,Object> map = new HashMap<String,Object>();
        if(c_list == null){
            map.put("code","-1");
            map.put("msg","暂无数据");
            map.put("count",0);
            map.put("data","[]");
        }
        else{
            map.put("code","0");
            map.put("msg","ok");
            map.put("count",c_list.size());
            map.put("data",c_list);
        }
        return map;
    }
}
