package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatDto {
    private String name;
    private LocalDate birthday;
}
