package com.example.points_system.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private LogInterceptor logInterceptor;
    
    @Autowired
    private AuthInterceptor authInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 日志拦截器（拦截所有）
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .order(1);
        
        // 登录校验拦截器（只拦截 /user/** 路径）
        // 具体的放行逻辑在 AuthInterceptor 内部处理
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/user/**")
                .order(2);
    }
}