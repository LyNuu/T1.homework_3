package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CatDto {
    private String name;
    private LocalDate birthday;
}
