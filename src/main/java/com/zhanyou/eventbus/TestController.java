package com.zhanyou.eventbus;

import com.google.common.eventbus.EventBus;
import com.zhanyou.eventbus.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event/bus")
public class TestController {
    @Autowired
    private EventBus eventBus;
    @PostMapping("/send")
    private void sendMessage() {
        eventBus.post(MessageEvent.of("1", "zhanyou"));
    }
}
