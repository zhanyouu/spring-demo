package com.zhanyou.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public SwServletFilter SwServletFilter() {
        return new SwServletFilter();
    }

//    @ConditionalOnMissingBean
//    @Bean
//    public FeignSwInterceptor feignSwInterceptor() {
//        return new FeignSwInterceptor();
//    }

}
