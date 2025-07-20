package org.example.command.module;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Command {
    private String description;
    private Priority priority;
    private String author;
    private String time;
}
