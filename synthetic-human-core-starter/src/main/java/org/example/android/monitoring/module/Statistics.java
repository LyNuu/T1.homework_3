package org.example.android.monitoring.module;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Statistics {
    private static final Map<String, Integer> tasksByAuthor = new ConcurrentHashMap<>();

    public static void incrementCompletedTasks(String author) {
        tasksByAuthor.merge(author, 1, Integer::sum);
    }

    public static Map<String, Integer> getAllCompleted() {
        return Collections.unmodifiableMap(tasksByAuthor);
    }
}
