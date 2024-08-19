package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateSpeciesRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.SpeciesDto;
import com.radomir.drazic.birdwatchingapp.entity.Genus;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.SpeciesMapper;
import com.radomir.drazic.birdwatchingapp.repository.GenusRepository;
import com.radomir.drazic.birdwatchingapp.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements ISpeciesService {
  private static final Logger logger = LoggerFactory.getLogger(SpeciesServiceImpl.class);
  private final SpeciesRepository repository;
  private final GenusRepository genusRepository;
  private final SpeciesMapper mapper;

  @Override
  public List<SpeciesDto> getAllSpecies() {
    List<Species> species = repository.findAll();
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public SpeciesDto getSpeciesById(Long id) {
    Species species = findSpeciesById(id);
    return mapper.toSpeciesDto(species);
  }

  @Override
  public SpeciesDto createSpecies(CreateSpeciesRequestDto speciesRequestDto) {
    Species speciesToSave = mapper.toEntityFromCreateSpeciesRequestDto(speciesRequestDto);
    Genus genus = findGenusById(speciesRequestDto.genusId());
    Species savedSpecies = repository.save(speciesToSave);
    savedSpecies.setGenus(genus);
    logger.info("Saved species is: {}", savedSpecies);
    return mapper.toSpeciesDto(savedSpecies);
  }

  @Override
  public SpeciesDto updateSpecies(Long id, CreateSpeciesRequestDto speciesRequestDto) {
    Species species = findSpeciesById(id);
    Genus genus = findGenusById(speciesRequestDto.genusId());
    species.setName(speciesRequestDto.name());
    species.setLatinName(speciesRequestDto.latinName());
    species.setGenus(genus);
    Species updatedSpecies = repository.save(species);
    return mapper.toSpeciesDto(updatedSpecies);
  }

  @Override
  @Transactional
  public void deleteSpeciesById(Long id) {
    findSpeciesById(id);
    repository.deleteById(id);
  }

  private Species findSpeciesById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> {
          logger.info("Species with an id {} not found!", id);
          return new ResourceNotFoundException("Species with id " + id + " not found!");
        }
    );
  }

  private Genus findGenusById(Long id) {
    return genusRepository.findById(id).orElseThrow(
        () -> {
          logger.info("Genus with an id {} not found!", id);
          return new ResourceNotFoundException("Genus with id " + id + " not found!");
        }
    );
  }
}