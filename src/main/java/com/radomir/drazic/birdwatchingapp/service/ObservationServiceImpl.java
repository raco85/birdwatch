package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import com.radomir.drazic.birdwatchingapp.entity.Observation;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.ObservationMapper;
import com.radomir.drazic.birdwatchingapp.repository.ObservationRepository;
import com.radomir.drazic.birdwatchingapp.repository.SpeciesRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObservationServiceImpl implements IObservationService{

  private static final Logger logger = LoggerFactory.getLogger(ObservationServiceImpl.class);
  private final ObservationRepository repository;
  private final SpeciesRepository speciesRepository;
  private final ObservationMapper mapper;

  @Override
  public List<ObservationDto> getAllObservations() {
    List<Observation> observations = repository.findAll();
    return observations.stream().map(mapper::toObservationDto).collect(Collectors.toList());
  }

  @Override
  public ObservationDto getObservationById(Long id) {
    Observation observation = findObservationById(id);
    return mapper.toObservationDto(observation);
  }

  @Override
  public List<ObservationDto> getAllObservationsBySpecies(Long speciesId) {
    Species species = findSpeciesById(speciesId);
    List<Observation> observations = repository.findAllBySpeciesSpeciesId(speciesId);
    if(observations.isEmpty())
      logger.info("There's no observations for {} species", species.getName());
    return observations.stream().map(mapper::toObservationDto).collect(Collectors.toList());
  }

  @Override
  public List<ObservationDto> getAllObservationsByUser(Long observerId) {
    List<Observation> observations = repository.findAllByObserverId(observerId);
    if(observations.isEmpty())
      logger.info("There's no observations for observer with an id {}", observerId);
    return observations.stream().map(mapper::toObservationDto).collect(Collectors.toList());
  }

  @Override
  public ObservationDto createObservation(CreateObservationRequestDto observationRequestDto) {
    Observation observationToSave = mapper
        .toEntityFromCreateObservationRequestDto(observationRequestDto);
    Species species = findSpeciesById(observationRequestDto.speciesId());
    Observation savedObservation = repository.save(observationToSave);
    savedObservation.setSpecies(species);
    return mapper.toObservationDto(savedObservation);
  }

  @Override
  public ObservationDto updateObservation(Long id,
      CreateObservationRequestDto observationRequestDto) {
    Observation observationToEdit = findObservationById(id);
    Species species = findSpeciesById(observationRequestDto.speciesId());
    observationToEdit.setLongitude(observationRequestDto.longitude());
    observationToEdit.setLatitude(observationRequestDto.latitude());
    observationToEdit.setDate(observationRequestDto.date());
    observationToEdit.setSpecies(species);
    Observation editedObservation = repository.save(observationToEdit);
    return mapper.toObservationDto(editedObservation);
  }

  @Override
  public void deleteObservation(Long id) {
    findObservationById(id);
    repository.deleteById(id);
  }

  private Observation findObservationById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> {
          logger.info("Observation with an id {} not found!", id);
          return new ResourceNotFoundException("Observation with an id " + id + " not found");
        }
    );
  }

  private Species findSpeciesById(Long id) {
    return speciesRepository.findById(id).orElseThrow(
        () -> {
          logger.info("Species with an id {} not found!", id);
          return new ResourceNotFoundException("Species with an id " + id + " not found");
        }
    );
  }
}