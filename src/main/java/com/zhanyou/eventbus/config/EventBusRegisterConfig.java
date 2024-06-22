//package com.zhanyou.eventbus.config;
//
//import com.google.common.eventbus.AsyncEventBus;
//import com.google.common.eventbus.EventBus;
//import com.zhanyou.eventbus.listener.EventListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class EventBusRegisterConfig {
//
//    @Autowired(required = false)
//    private void register(EventBus eventBus, AsyncEventBus asyncEventBus, List<EventListener> eventListeners) {
//        for (EventListener eventListener : eventListeners) {
//            eventBus.register(eventListener);
//            asyncEventBus.register(eventListener);
//        }
//    }
//}
