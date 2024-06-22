package com.zhanyou.spring_04;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class UserService {
    @PostConstruct
    public void init(){
        log.info("PostConstruct init...");
    }
}
