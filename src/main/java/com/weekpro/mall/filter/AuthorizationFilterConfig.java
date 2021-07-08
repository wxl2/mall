package com.weekpro.mall.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxl
 * @date 2021/6/27 下午8:29
 * @packageName com.weekpro.mall.filter
 * TODO
 */
//@Configuration
public class AuthorizationFilterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AuthorizationFilter());
        interceptorRegistration.addPathPatterns("/*");
        interceptorRegistration.addPathPatterns("/admin/*");
        interceptorRegistration.addPathPatterns("/store/*");
        List<String> filter = new ArrayList<String>();
        filter.add("/index.html");
        filter.add("/getSysManageLoginCode");
        filter.add("/register");
        filter.add("/login");
        filter.add("//");
        filter.add("/store/applyStatus.html");
        filter.add("/store/StoreManger.html");
        interceptorRegistration.excludePathPatterns(filter);
    }
}
