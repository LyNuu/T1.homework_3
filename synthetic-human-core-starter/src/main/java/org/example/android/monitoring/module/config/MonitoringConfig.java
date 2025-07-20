package org.example.android.monitoring.module.config;

import org.example.android.monitoring.module.MonitoringService;
import org.example.android.monitoring.module.Statistics;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class MonitoringConfig {
    @Bean
    public MonitoringService monitoringService() {
        return new MonitoringService();
    }

    @Bean
    public Statistics statistics() {
        return new Statistics();
    }
}
