package com.zhanyou.eventbus.event;

import lombok.Data;

@Data
public class MessageEvent2 {
    private String id;
    private String name;

    public MessageEvent2(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MessageEvent2 of(String id, String name) {
        return new MessageEvent2(id, name);
    }
}
