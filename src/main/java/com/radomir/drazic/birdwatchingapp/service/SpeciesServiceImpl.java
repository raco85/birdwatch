package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateSpeciesRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.SpeciesDto;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import com.radomir.drazic.birdwatchingapp.mapper.SpeciesMapper;
import com.radomir.drazic.birdwatchingapp.repository.SpeciesRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements ISpeciesService {

  private final SpeciesRepository repository;
  private final SpeciesMapper mapper;

  @Override
  public List<SpeciesDto> getAllSpecies() {
    List<Species> species = repository.findAll();
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public SpeciesDto getSpeciesById(Long id) {
    Species species = repository.findById(id).orElseThrow();
    return mapper.toSpeciesDto(species);
  }

  @Override
  public SpeciesDto createSpecies(CreateSpeciesRequestDto speciesRequestDto) {
    Species speciesToSave = mapper.toEntityFromCreateSpeciesRequestDto(speciesRequestDto);
    Species savedSpecies = repository.save(speciesToSave);
    return mapper.toSpeciesDto(savedSpecies);
  }

  @Override
  public SpeciesDto updateSpecies(Long id, CreateSpeciesRequestDto speciesRequestDto) {
    Species species = repository.findById(id).orElseThrow();
    species.setName(speciesRequestDto.name());
    species.setLatinName(speciesRequestDto.latinName());
    Species updatedSpecies = repository.save(species);
    return mapper.toSpeciesDto(updatedSpecies);
  }

  @Override
  public void deleteSpeciesById(Long id) {
    repository.deleteById(id);
  }
}