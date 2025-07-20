package org.example.model.dto.mapper;

import org.example.model.Cat;
import org.example.model.dto.CatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CatMapper {
    CatDto toDto(Cat cat);

    @Mapping(target = "id", ignore = true)
    Cat toEntity(CatDto catDto);
}
