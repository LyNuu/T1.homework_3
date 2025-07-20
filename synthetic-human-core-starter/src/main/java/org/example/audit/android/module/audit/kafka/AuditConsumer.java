package org.example.audit.android.module.audit.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableKafka
public class AuditConsumer {
    @KafkaListener(topics = "audit-topic-2", groupId = "audit-topic-2")
    public void auditListener(String audit) {
        log.info("Полученное сообщение:\n{}\n kafka", audit);
    }
}
