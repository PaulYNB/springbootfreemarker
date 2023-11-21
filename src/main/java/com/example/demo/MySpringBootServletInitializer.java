package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//todo: to extends SpringBootServletInitializer, and add MySpringBootApplication into SpringApplication.sources
public class MySpringBootServletInitializer extends SpringBootServletInitializer {
	private static Logger logger = LogManager.getLogger(MySpringBootServletInitializer.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	System.out.println("MySpringBootServletInitializer.configure()!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	logger.debug("MySpringBootServletInitializer.configure()<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return builder.sources(HelloSpringBootApplication.class);
    }	
  
}
