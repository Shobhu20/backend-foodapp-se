package com.windsor.foodapp.config;

import com.windsor.foodapp.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserInterceptor getUserInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor()).addPathPatterns("/user/*");
    }
}
