package com.zhanyou.eventbus.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.zhanyou.eventbus.listener.EventListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public class EventBusRegister {
    @Autowired
    private EventBus eventBus;
    @Autowired
    private AsyncEventBus asyncEventBus;
    @Autowired
    private List<EventListener> eventListeners;

    @PostConstruct
    public void init(){
        for (EventListener eventListener : eventListeners) {
            eventBus.register(eventListener);
            asyncEventBus.register(eventListener);
        }
    }

//    @Override
//    public void run(String... args) {
//        for (EventListener eventListener : eventListeners) {
//            eventBus.register(eventListener);
//            asyncEventBus.register(eventListener);
//        }
//    }
}
