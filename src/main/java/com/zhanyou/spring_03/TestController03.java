package com.zhanyou.spring_03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spring/03")
public class TestController03 {
    @PostMapping("/send")
    private void test(@RequestBody UserModel userModel) {
        log.info(userModel.toString());
    }
}
