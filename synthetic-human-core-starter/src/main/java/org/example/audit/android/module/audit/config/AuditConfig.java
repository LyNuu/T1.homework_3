package org.example.audit.android.module.audit.config;

import org.example.audit.android.module.audit.AuditLogger;
import org.example.audit.android.module.audit.aspect.WeylandWatchingYouAspect;
import org.example.audit.android.module.audit.console.ConsoleAudit;
import org.example.audit.android.module.audit.kafka.AuditConsumer;
import org.example.audit.android.module.audit.kafka.KafkaAudit;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@AutoConfiguration
@Configuration
@EnableAspectJAutoProxy
public class AuditConfig {
    @Bean
    public WeylandWatchingYouAspect weylandWatchingYouAspect(ApplicationContext applicationContext) {
        return new WeylandWatchingYouAspect(applicationContext);
    }

    @Bean("console")
    public AuditLogger consoleAuditLogger() {
        return new ConsoleAudit();
    }

    @Bean("kafka")
    public AuditLogger kafkaAuditLogger() {
        return new KafkaAudit();
    }

    @Bean
    public AuditConsumer auditConsumer() {
        return new AuditConsumer();
    }
}
