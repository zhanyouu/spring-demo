package com.zhanyou.log.test;

import com.zhanyou.log.AttaLogger;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @AttaLogger(name = "LogService", value = {"${param}", "${ret}"})
    public String getLog(String id) {
        return "hello";
    }
}
