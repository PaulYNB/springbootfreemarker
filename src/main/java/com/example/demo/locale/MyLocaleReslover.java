package com.example.demo.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

public class MyLocaleReslover implements LocaleResolver {
	
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String localeStr = httpServletRequest.getParameter("locale");
//        Locale locale = Locale.getDefault();//获取默认语言环境
        Locale locale = httpServletRequest.getLocale();//获取请求客户端的语言环境
        if (localeStr != null && !StringUtils.isEmpty(localeStr)) {
            String[] split = localeStr.split("_");
            locale = new Locale(split[0], split[1]);
        }
        System.out.println("LocaleResolver.resolveLocale()!!!!!!!!!!!!!!!!!!!!!!");
        String reqstURI = httpServletRequest.getRequestURI();
        System.out.println("httpServletRequest:" + reqstURI);
        return locale;
    }
 
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, 
    		HttpServletResponse httpServletResponse, Locale locale) {
 
    }
}

