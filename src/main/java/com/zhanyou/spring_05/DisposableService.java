package com.zhanyou.spring_05;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DisposableService implements DisposableBean {
    @Override
    public void destroy() {
        log.info("container begin destroy...");
    }
}
