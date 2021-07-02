package com.tssf.blog.config;

import com.tssf.blog.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginIntercepter());
        interceptor.addPathPatterns("/admin/**");
        interceptor.excludePathPatterns("/admin").excludePathPatterns("/admin/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
