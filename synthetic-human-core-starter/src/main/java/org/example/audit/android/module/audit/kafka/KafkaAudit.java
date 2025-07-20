package org.example.audit.android.module.audit.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.audit.android.module.audit.Audit;
import org.example.audit.android.module.audit.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("kafka")
public class KafkaAudit implements AuditLogger {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void log(String methodName, Object[] args, Object result) throws JsonProcessingException {
        String audit = objectMapper.writeValueAsString(Audit.builder()
                .methodName(methodName)
                .args(args)
                .result(result)
                .build());
        kafkaTemplate.send("audit-topic-2", audit);
    }
}
