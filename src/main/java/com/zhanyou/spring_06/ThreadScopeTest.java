package com.zhanyou.spring_06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spring/06")
public class ThreadScopeTest {
    @Autowired
    private ThreadScopeService threadScopeService;
    @Autowired
    private ApplicationContext context;

    /**
     * Thread-11] : com.zhanyou.spring_06.ThreadScopeService@519c7113
     * Thread-10] : com.zhanyou.spring_06.ThreadScopeService@519c7113
     */
    @PostMapping("/test")
    private void test() {
        new Thread(() -> {
            log.info("threadName:{},bean:{}",Thread.currentThread().getName(),threadScopeService);
        }).start();
        new Thread(() -> {
            log.info("threadName:{},bean:{}",Thread.currentThread().getName(),threadScopeService);
        }).start();
    }

    @PostMapping("/test2")
    private void test2() {
        new Thread(() -> {
            log.info("threadName:{},bean:{}",Thread.currentThread().getName(),context.getBean(ThreadScopeService.class));
            log.info("threadName:{},bean:{}",Thread.currentThread().getName(),context.getBean(ThreadScopeService.class));
        }).start();
        new Thread(() -> {
            log.info("threadName:{},bean:{}",Thread.currentThread().getName(),context.getBean(ThreadScopeService.class));
            log.info("threadName:{},bean:{}",Thread.currentThread().getName(),context.getBean(ThreadScopeService.class));
        }).start();
    }
}
