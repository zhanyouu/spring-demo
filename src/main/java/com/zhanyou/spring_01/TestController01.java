package com.zhanyou.spring_01;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/01")
public class TestController01 {
    @PostMapping("/send")
    private void test() {
        System.out.println("hello");
    }
}
