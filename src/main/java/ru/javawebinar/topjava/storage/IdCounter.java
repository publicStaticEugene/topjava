package ru.javawebinar.topjava.storage;

import java.util.concurrent.atomic.AtomicInteger;

public class IdCounter {
    private static final AtomicInteger id = new AtomicInteger(0);

    public static int getId() {
        return id.incrementAndGet();
    }
}
