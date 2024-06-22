package com.zhanyou.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import com.zhanyou.eventbus.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener implements EventListener {
    @Subscribe
    public void onEvent(MessageEvent event) {
        log.info("MessageEventListener event:{}", event);
    }
}
