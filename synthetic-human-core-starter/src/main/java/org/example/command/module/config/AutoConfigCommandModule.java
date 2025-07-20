package org.example.command.module.config;

import org.example.command.module.ConsumerCommand;
import org.example.command.module.ProducerCommand;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class AutoConfigCommandModule {
    @Bean
    public ProducerCommand producerCommand() {
        return new ProducerCommand();
    }

    @Bean
    public ConsumerCommand consumerCommand() {
        return new ConsumerCommand();
    }
}
