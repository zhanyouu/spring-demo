package com.zhanyou.spring_06;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 线程级别作用域
 */
public class ThreadLocalScope implements Scope {
    ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        Object instance = threadLocal.get();
        if (instance != null) {
            return instance;
        }
        Object object = objectFactory.getObject();
        threadLocal.set(object);
        return object;
    }

    @Override
    public Object remove(String s) {
        threadLocal.remove();
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
