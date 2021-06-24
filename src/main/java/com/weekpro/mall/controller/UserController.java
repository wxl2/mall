package com.weekpro.mall.controller;

import com.weekpro.mall.User;
import com.weekpro.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author wxl
 * @date 2021/6/17 下午4:11
 * @packageName com.weekpro.mall.controller
 * TODO
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> map, HttpServletResponse response, HttpServletRequest request){
        String code = (String) map.get("code");
        String username = (String)map.get("loginUsername");
        String passwd = (String)map.get("loginPassword");
        Map<String, Object> res = new HashMap<String, Object>();
        if(!RandomValidateCode.checkCode(request,response,code)){
            res.put("status","falid");
            res.put("data","验证码错误");
            return res;
        }
        User user = new User(username,passwd,"");
        String data = userService.login(user);
        if(data.equals("-1")) {
            res.put("status", "falid");
            res.put("data", "用户名或密码错误");
        }else if(data.equals("1")){
            res.put("status", "success");
            res.put("data", "admin.html");
        }else{
            res.put("status", "success");
            res.put("data", "user.html");
        }
        return res;
    }
    @PostMapping("/register")
    public String register(@RequestBody Map<String,Object> map){
        String userName = (String) map.get("registerUsername");
        String passwd = (String) map.get("registerPassword");
        String WellPasswd = (String) map.get("registerWellPassword");
        if(!passwd.equals(WellPasswd)){
            return "两次密码输入不一致";
        }
        User user = new User(userName,passwd,"0");
        if(userService.register(user)==0)
            return "注册成功";
        else return "注册失败";
    }
}
