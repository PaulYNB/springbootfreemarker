package com.example.demo.locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class MyWebMvcConfiguration implements WebMvcConfigurer {
	
//	@Value("${spring.mvc.locale}")
//	boolean isLocale;
    
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty( 
            prefix = "spring.mvc",
            name = {"localeChange"},
            havingValue = "true"
        )
    public LocaleResolver localeResolver() {
    	System.out.println("WebMvcConfigurer.localeResolver()!!!!!!!!!!!!!!!!!");
        return new MyLocaleReslover();
    }

}
