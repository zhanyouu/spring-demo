package com.zhanyou.log.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spring/log")
public class LogTestController {
    private static final Logger logger = LogManager.getLogger(LogTestController.class);

    @Autowired
    private LogService logService;

    @PostMapping("/send")
    private void test() {
        logger.info("info...");
        logService.getLog("123");
    }
}
