package com.example.demo;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.service.UserService;
@MapperScan("com.example.demo.mapper")
//@SpringBootApplication(scanBasePackages= {"com.example.demo.controller","com.example.demo.service","com.example.demo.interceptor","com.example.demo.handler"})
//@EnableAsync
@SpringBootApplication
public class HelloSpringBootApplication //extends WebMvcConfigurerAdapter{
{
	@Autowired
	@Qualifier("userServiceImpl2")  //适用于同一接口多个实现的情况。 
	UserService userService;
	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		//创建FastJson的消息转换器
		FastJsonHttpMessageConverter convert = new FastJsonHttpMessageConverter();
		//创建FastJson的配置对象
		FastJsonConfig config = new FastJsonConfig();
		//对Json数据进行格式化
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		convert.setFastJsonConfig(config);
		converters.add(convert);
	}*/
	
	@Bean
	public HttpMessageConverters fastJsonMessageConverter() {
		
		//创建FastJson的消息转换器
		FastJsonHttpMessageConverter convert = new FastJsonHttpMessageConverter();
		//创建FastJson的配置对象
		FastJsonConfig config = new FastJsonConfig();
		//对Json数据进行格式化
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		convert.setFastJsonConfig(config);
		HttpMessageConverter<?> con =convert;
		return new HttpMessageConverters(con);
	}
	
	public static void main(String[] args) {
		System.out.println("入口APP当前线程>>>>>>>" + Thread.currentThread().getName());
		ConfigurableApplicationContext springCtx = 
				SpringApplication.run(HelloSpringBootApplication.class);
	      //一个类型有多个实例，可以使用它返回多个实例的名称
	      String[] names = springCtx.getBeanNamesForType(UserService.class);   
	      for (String name : names) {
	          System.out.println("bean -- HelloSpringBootApplication:>>>>" + name);
	      }
	      UserService appBean = 
	    		  springCtx.getBean(UserService.class);  
//	      UserService appBean =
//		  		(UserService)springCtx.getBean("userServiceImpl2"); 
	      String className = appBean.getClass().getName();
	      System.out.println("className -- HelloSpringBootApplication:>>>>" + className);
		//查询出容器中所有的实例对象名称
		String[] beanDefinitionNames = springCtx.getBeanDefinitionNames();   
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("beanDefinitionName: " + beanDefinitionName);
		}		
	}

}
