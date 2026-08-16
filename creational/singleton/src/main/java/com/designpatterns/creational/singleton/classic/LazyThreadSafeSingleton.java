package com.designpatterns.creational.singleton.classic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code instance} must be {@code volatile}: without it, a thread could observe a
 * non-null reference to an object whose constructor hasn't finished writing its
 * fields yet, because the JMM allows the write to {@code instance} to be reordered
 * ahead of the writes inside the constructor.
 */
public final class LazyThreadSafeSingleton {

    private static volatile LazyThreadSafeSingleton instance;

    private final Map<String, String> settings;

    private LazyThreadSafeSingleton() {
        this.settings = new ConcurrentHashMap<>();
        this.settings.put("environment", "production");
        this.settings.put("region", "sa-east-1");
    }

    public static LazyThreadSafeSingleton getInstance() {
        LazyThreadSafeSingleton result = instance;
        if (result == null) {
            synchronized (LazyThreadSafeSingleton.class) {
                result = instance;
                if (result == null) {
                    instance = result = new LazyThreadSafeSingleton();
                }
            }
        }
        return result;
    }

    public String getSetting(String key) {
        return settings.get(key);
    }
}
