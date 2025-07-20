package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.model.Cat;
import org.example.model.dto.CatDto;
import org.example.model.dto.mapper.CatMapper;
import org.example.repositoty.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatService {
    private final CatRepository catRepository;
    private final CatMapper catMapper;

    public CatDto save(CatDto catDto) {
        Cat cat = catMapper.toEntity(catDto);
        Cat savedCat = catRepository.save(cat);
        return catMapper.toDto(savedCat);
    }

    public CatDto getById(Long id) {
        return catRepository.findById(id)
                .map(catMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with id: " + id));
    }

    public List<CatDto> getAll() {
        return catRepository.findAll().stream()
                .map(catMapper::toDto)
                .collect(Collectors.toList());
    }
}
