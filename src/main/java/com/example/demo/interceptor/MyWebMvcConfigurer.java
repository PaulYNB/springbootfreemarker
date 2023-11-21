package com.example.demo.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//@Configuration//声明这是一个配置
@Component
public class MyWebMvcConfigurer implements WebMvcConfigurer 
{

    @Resource
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;
    
    @Override
    /*
     * AsyncSupportConfigurer: 请求异步返回（callable, deferredResult, ）配置器
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureAsyncSupport(org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer)
     */
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        // 处理callable超时
        configurer.setDefaultTimeout(60 * 1000);  
        //asynchronous request processing
        configurer.setTaskExecutor(myThreadPoolTaskExecutor);
        // todo: test timeout, can overrid handleTimeout(...).
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
    }
	
	@Override  //test remove this annotation.
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		HandlerInterceptor inter = new HandlerInterceptor() {

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				String contextPath = request.getRequestURI();
				String locale = request.getParameter("locale");
				System.out.println("自定义拦截器>>>>>>>>>");
				System.out.println("contextPath: " + contextPath);
				System.out.println("locale: " + locale);
				System.out.println("自定义拦截器<<<<<<<<<");
				return true;
			}

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				// TODO Auto-generated method stub
				HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
					Exception ex) throws Exception {
				// TODO Auto-generated method stub
				HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
			}

			
			
		};
		//拦截器的注册
		registry.addInterceptor(inter).addPathPatterns("/**");//拦截所有
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/freemarker")
        		.excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**"); //放行的请求
	}
	
    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }	

}
