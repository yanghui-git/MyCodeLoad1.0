package com.yanghui.test.filter;

public class ThreadLoacalUtil {

    private static ThreadLocal<OpenrationUser> openrationUserThreadLocal = new ThreadLocal<>();

    public static OpenrationUser get() {
        return openrationUserThreadLocal.get();
    }

    public static void set(OpenrationUser openrationUser) {
        openrationUserThreadLocal.set(openrationUser);
    }
}
