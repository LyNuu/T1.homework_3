package org.example.command.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerCommand {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendCommand(Command command) throws JsonProcessingException {
        if (Priority.CRITICAL.equals(command.getPriority())) {
            String event = objectMapper.writeValueAsString(command);
            log.info("Полученное сообщение:\n{}", event);
        } else {
            sendEvent(command);
        }
    }

    private void sendEvent(Command command) throws JsonProcessingException {
        String event = objectMapper.writeValueAsString(command);
        kafkaTemplate.send("command-topic", event);
    }
}
