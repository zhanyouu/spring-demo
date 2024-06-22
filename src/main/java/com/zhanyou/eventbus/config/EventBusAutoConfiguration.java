package com.zhanyou.eventbus.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class EventBusAutoConfiguration {
    @Bean("executor")
    @ConditionalOnMissingBean
    public Executor executor() {
        return new ThreadPoolExecutor(4, 4, 100,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));
    }

    @Bean
    @ConditionalOnMissingBean
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncEventBus asyncEventBus(Executor executor) {
        return new AsyncEventBus(executor);
    }

    @Bean
    @ConditionalOnMissingBean
    public EventBusRegister eventBusRegister() {
        return new EventBusRegister();
    }
}
