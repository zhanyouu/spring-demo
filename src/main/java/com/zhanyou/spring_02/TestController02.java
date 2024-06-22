package com.zhanyou.spring_02;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/02")
public class TestController02 {
    @PostMapping("/send")
    private String test() {
        System.out.println("hello");
        int a = 1/0;
        return "hell0";
    }
}
