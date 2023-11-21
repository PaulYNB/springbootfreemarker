package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * 1、编写一个拦截器实现HandlerInterceptor接口
 * 2、注册拦截器到容易中（实现addInterceptors）
 * 3、指定拦截规则
 */
@Configuration
@Deprecated
//Deprecated. The single WebMvcConfigurer is under package 
//com.example.demo.interceptor
public class AdminWebConfig //implements WebMvcConfigurer 
{ 
 
    //@Override
    //配置拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        //拦截/**全部，放行/ /login
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/freemarker") //所有请求都会被拦截，包括静态资源
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**"); //放行的请求
    }
}
