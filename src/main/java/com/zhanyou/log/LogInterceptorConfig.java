package com.zhanyou.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LogInterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AttaApiInterceptor getAttaApiInterceptor() {
        return new AttaApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getAttaApiInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
