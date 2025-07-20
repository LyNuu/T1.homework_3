package org.example.command.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableKafka
public class ConsumerCommand {
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "command-topic", groupId = "command-topic")
    public void eventListener(String event) {
        log.info("Полученное сообщение:\n{}", event);
    }
}
