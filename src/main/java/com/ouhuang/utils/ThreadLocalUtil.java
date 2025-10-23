package com.ouhuang.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ThreadLocal;
import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();
    private static final Logger log = LoggerFactory.getLogger(ThreadLocalUtil.class);

    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    public static void set(Object obj) {
        THREAD_LOCAL.set(obj);
    }

    public static Long getUid() {
        // System.out.println(THREAD_LOCAL.get());
        try {
            Map<String, Object> payload = (Map<String, Object>) THREAD_LOCAL.get();
            return ((Integer) payload.get("user_id")).longValue();
        } catch (Exception e) {
            return -1L;
        }
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
