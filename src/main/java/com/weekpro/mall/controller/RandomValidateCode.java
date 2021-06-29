package com.weekpro.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @author wxl
 * @date 2021/6/17 下午4:13
 * @packageName com.weekpro.mall.controller
 * TODO
 */
@Controller
public class RandomValidateCode {
    private Random random = new Random();
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private int stringNum = 4;

    //获取验证码
    @RequestMapping(value = "/getSysManageLoginCode",method = {RequestMethod.GET})
    @ResponseBody
    public String getRandStringCode(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        String code ="";
        for(int i =0 ;i<stringNum;i++){
            code+=randString.valueOf(randString.charAt(random.nextInt(62)));
        }
        String sessionid = request.getSession().getId();
        request.getSession().setAttribute(sessionid+"code", code);
        try {
            response.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }
    //校验验证码
    public static boolean checkCode(HttpServletRequest request,HttpServletResponse response,String inputCode){
        String code = null;
        String sessionid = request.getSession().getId();
        code = (String)request.getSession().getAttribute(sessionid+"code");
        if(!code.equals(inputCode))
            return false;
        return true;
    }
}
