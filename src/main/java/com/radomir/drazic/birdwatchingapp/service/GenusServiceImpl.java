package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateGenusRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.GenusDto;
import com.radomir.drazic.birdwatchingapp.entity.Genus;
import com.radomir.drazic.birdwatchingapp.mapper.GenusMapper;
import com.radomir.drazic.birdwatchingapp.repository.GenusRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenusServiceImpl implements IGenusService{

  private final GenusRepository repository;
  private final GenusMapper mapper;

  @Override
  public List<GenusDto> getAllGeneses() {
    List<Genus> geneses = repository.findAll();
    return geneses.stream().map(mapper::toGenesesDto).collect(
        Collectors.toList());
  }

  @Override
  public GenusDto getGenusById(Long id) {
    Genus genus = repository.findById(id).orElseThrow();
    return mapper.toGenesesDto(genus);
  }

  @Override
  public GenusDto createGenus(CreateGenusRequestDto genusRequestDto) {
    Genus genusToSave = mapper.toEntityFromCreateGenusDto(genusRequestDto);
    Genus savedGenus = repository.save(genusToSave);
    return mapper.toGenesesDto(savedGenus);
  }

  @Override
  public GenusDto updateGenus(Long id, CreateGenusRequestDto genusRequestDto) {
    Genus genusToUpdate = repository.findById(id).orElseThrow();
    genusToUpdate.setName(genusRequestDto.name());
    genusToUpdate.setLatinName(genusRequestDto.latinName());
    Genus updatedGenus = repository.save(genusToUpdate);
    return mapper.toGenesesDto(updatedGenus);
  }

  @Override
  public void deleteGenus(Long id) {
    repository.deleteById(id);
  }
}