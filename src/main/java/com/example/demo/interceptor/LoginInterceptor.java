package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * 登录检查
 * 1、配置拦截器要拦截哪些请求
 * 2、把这些配置放在容器里
 */
public class LoginInterceptor implements HandlerInterceptor {
 
    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	boolean returnValue = true;
    	//获取当前的请求路径
        String requestURI = request.getRequestURI();
        //打印日志
        System.out.println("LoginInterceptor拦截的请求路径是: " + requestURI);
        System.out.println("handler: " + handler.getClass());
        //登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            //登录成功
            return returnValue;
        }
        //拦截住，未登录，跳转到登录页
//        request.setAttribute("msg","请先登录");
//        request.getRequestDispatcher("/login.jsp").forward(request,response);
//        returnValue = false;
        return returnValue;
    }
 
    /**
     * 方法目标执行之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
 
    }
 
    
    
}
