package com.zhanyou.eventbus.event;

import lombok.Data;

@Data
public class MessageEvent {
    private String id;
    private String name;

    public MessageEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MessageEvent of(String id, String name) {
        return new MessageEvent(id, name);
    }
}
