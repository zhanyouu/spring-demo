package com.zhanyou.log;

public class SwContext {

    private static final ThreadLocal<String> currentSw = new ThreadLocal<>();

    public static String getCurrentSw() {
        return currentSw.get();
    }

    public static void setCurrentSw(String link) {
        currentSw.set(link);
    }

    public static void clear() {
        currentSw.remove();
    }

}
