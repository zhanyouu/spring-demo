package com.zhanyou.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author
 */
@Configuration(value="logAutoConfiguration")
public class AutoConfiguration {

    @Bean
    public LogAutoProxyCreator logAutoProxyCreator(){
        return new LogAutoProxyCreator();
    }

    @ConditionalOnMissingBean
    @Bean
    public LogInterceptorConfig logInterceptorConfig() {
        return new LogInterceptorConfig();
    }

    @Bean
    public LoggingGlobalExceptionHandler loggingGlobalExceptionHandler() {
        return new LoggingGlobalExceptionHandler();
    }
}
