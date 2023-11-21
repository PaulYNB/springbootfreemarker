package com.example.demo.controller;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.pojo.User;

@Controller
//@RestController
public class TestFreeMarker {
	
	@Resource
	private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;
	
	@RequestMapping("/asyncRtn")
	@ResponseBody
	public Callable<String> order() {
	    System.out.println("主线程开始：" + Thread.currentThread().getName());
	    Callable<String> result = () -> {
	        System.out.println("副线程开始：" + Thread.currentThread().getName());
	        Thread.sleep(5000);
	        System.out.println("副线程返回：" + Thread.currentThread().getName());
	        return "success";
	    };
	    System.out.println("主线程返回：" + Thread.currentThread().getName());
	    //！！！！！！！！！！！！！！！！！！！！！！！！！！！
	    //待callable执行完返回结果后，spring重新打开一个新线程，重新发送一次request，并把结果返回给前端。
	    return result;
	}
	
	@RequestMapping(value = "/asyncRtn1", method = RequestMethod.GET)
	@ResponseBody
	public WebAsyncTask<String> webAsyncReq () {
		 System.out.println("外部线程：" + Thread.currentThread().getName());
		 Callable<String> result = () -> {
			  System.out.println("内部线程开始：" + Thread.currentThread().getName());
			  try {
			      TimeUnit.SECONDS.sleep(3);
			  } catch (Exception e) {
			      // TODO: handle exception
				  throw e;
			  }
			  System.out.println("内部线程返回：" + Thread.currentThread().getName());
			  return "success";
		 };
		 WebAsyncTask<String> wat = new WebAsyncTask<String>(5000L, result);
		 wat.onTimeout(new Callable<String>() {
			  @Override
			  public String call() throws Exception {
			   // TODO Auto-generated method stub
			   return "超时";
			  }
		 });
		 wat.onCompletion(new Runnable() {
			 @Override
			 public void run() {
			   System.out.println("调用完成：" + Thread.currentThread().getName());
			 }
		 });
		 return wat;
	}
	
	@RequestMapping(value = "/asyncRtn2", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<String> deferredResultReq () {
		 System.out.println("外部线程：" + Thread.currentThread().getName());
		 //设置超时时间
		 DeferredResult<String> result = new DeferredResult<String>(60*1000L);
		 //处理超时事件 采用委托机制
		 result.onTimeout(new Runnable() {
			  @Override
			  public void run() {
				   System.out.println("DeferredResult超时");
				   result.setResult("超时了!");
			  }
		 });
		 result.onCompletion(new Runnable() {
			 @Override
			 public void run() {
			   System.out.println("调用完成：" + Thread.currentThread().getName());
			 }
		 });
//		 myThreadPoolTaskExecutor.execute(new Runnable() {
		 myThreadPoolTaskExecutor.execute(() -> { 
//			 @Override
//			 public void run() {
				 try{
				   //处理业务逻辑
				   System.out.println("内部线程：" + Thread.currentThread().getName());
				   Thread.sleep(3000);
				   //返回结果
				   result.setResult("DeferredResult!!");
				 } catch(Exception e) {
				   e.printStackTrace();
				 }
//			 }
		 });
		 System.out.println("外部线程1：" + Thread.currentThread().getName());
		 return result;
	}	
	
	/*
	 * JAVA自带的Servlet方式实现请求异步返回。
	 */
	@RequestMapping(value = "/asyncRtn3", method=RequestMethod.GET)
	public void servletReq (HttpServletRequest request, HttpServletResponse response) {
		  AsyncContext asyncContext = request.startAsync();
		  //设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
		  asyncContext.addListener(new AsyncListener() {
		   @Override
		   public void onTimeout(AsyncEvent event) throws IOException {
		    System.out.println("超时了...");
		    //做一些超时后的相关操作...
		   }
		   @Override
		   public void onStartAsync(AsyncEvent event) throws IOException {
		    System.out.println("线程开始");
		   }
		   @Override
		   public void onError(AsyncEvent event) throws IOException {
		    System.out.println("发生错误："+event.getThrowable());
		   }
		   @Override
		   public void onComplete(AsyncEvent event) throws IOException {
		    System.out.println("执行完成");
		    //这里可以做一些清理资源的操作...
		   }
		  });
		  //设置超时时间
		  asyncContext.setTimeout(20000);
		  asyncContext.start(new Runnable() {
		   @Override
		   public void run() {
			    try {
				     Thread.sleep(10000);
				     System.out.println("内部线程：" + Thread.currentThread().getName());
				     asyncContext.getResponse().setCharacterEncoding("utf-8");
				     asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
				     asyncContext.getResponse().getWriter().println("这是异步的请求返回");
			    } catch (Exception e) {
			     System.out.println("异常："+e);
			    }
			    //异步请求完成通知
			    //此时整个请求才完成
			    asyncContext.complete();
		   }
	  });
	  //此时之类 request的线程连接已经释放了
	  System.out.println("主线程：" + Thread.currentThread().getName());
	 }

	@RequestMapping(value="/freemarker")
	public String show(Model model) {	
		model.addAttribute("name","霸道流氓气质");
		model.addAttribute("locale","en_US");
		System.out.println("当前线程>>>>>>>" + Thread.currentThread().getName());
		return "show";  //show.ftl  
	} 
	
	@RequestMapping("/freemarker1")
	public ModelAndView show1(Model md) {
		ModelAndView mv = new ModelAndView("show");  //show.ftl 
		mv.getModel().put("name","wo霸道流氓气质");
		return mv;
	}
	
    @RequestMapping("/freemarker2")
    public ModelAndView indexFreeMarker(ModelMap map) {
        ModelAndView mv = new ModelAndView("show"); //show.ftl
        map.addAttribute("name","王老师大笨蛋");
        map.addAttribute("host", "http://www.baidu.com");
        return mv;
    }
    
	@RequestMapping("/freemarker3")
	@ResponseBody
	public User show3(Model model) {		
		model.addAttribute("name","霸道流氓气质");
		User user = new User();
		user.setPassword("abc");
		user.setUsername("userNamePaul");
		return user;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Callable<String> processUpload(final MultipartFile file) {

	    return new Callable<String>() {
	        public String call() throws Exception {
	            // ...
	            return "someView";
	        }
	    };

	}	
	
}
