package org.example.android.monitoring.module;

import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class MonitoringService {
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4, 8, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
    );

    public void submitTask(Runnable task) {
        executor.submit(task);
    }

    public int getQueueSize() {
        return executor.getQueue().size();
    }
}
