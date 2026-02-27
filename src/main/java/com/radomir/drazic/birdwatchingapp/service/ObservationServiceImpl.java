package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.request.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.IndividualBirdRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.ObservationRadiusFilterRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import com.radomir.drazic.birdwatchingapp.entity.IndividualBird;
import com.radomir.drazic.birdwatchingapp.entity.Observation;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.IndividualBirdMapper;
import com.radomir.drazic.birdwatchingapp.mapper.ObservationMapper;
import com.radomir.drazic.birdwatchingapp.repository.IndividualBirdRepository;
import com.radomir.drazic.birdwatchingapp.repository.ObservationRepository;
import com.radomir.drazic.birdwatchingapp.repository.SpeciesRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ObservationServiceImpl implements IObservationService {

  private static final Logger logger = LoggerFactory.getLogger(ObservationServiceImpl.class);
  private final ObservationRepository repository;
  private final SpeciesRepository speciesRepository;
  private final IndividualBirdRepository individualBirdRepository;
  private final ObservationMapper observationMapper;
  private final IndividualBirdMapper individualBirdMapper;

  @Override
  public List<ObservationDto> getAllObservations() {
    List<Observation> observations = repository.findAll();
    return observations.stream().map(observationMapper::toObservationDto).collect(Collectors.toList());
  }

  @Override
  public ObservationDto getObservationById(Long id) {
    Observation observation = findObservationById(id);
    return observationMapper.toObservationDto(observation);
  }

  @Override
  public List<ObservationDto> getAllObservationsByUser(Long observerId) {
    List<Observation> observations = repository.findAllByObserverId(observerId);
    if(observations.isEmpty())
      logger.info("There's no observations for observer with an id {}", observerId);
    return observations.stream().map(observationMapper::toObservationDto).collect(Collectors.toList());
  }

  @Override
  public List<ObservationDto> getAllObservationsByRadius(ObservationRadiusFilterRequestDto radiusFilterRequestDto) {
    List<Observation> observations = repository.findAll();

    List<ObservationDto> observationDtos = observations.stream().filter(observation ->
            haversineFormula(observation.getLatitude(), observation.getLongitude(),
                    radiusFilterRequestDto.latitude(), radiusFilterRequestDto.longitude()) <= radiusFilterRequestDto.distance()
    ).map(observationMapper::toObservationDto).toList();
      return observationDtos;
  }

  @Override
  public ObservationDto createObservation(CreateObservationRequestDto observationRequestDto) {
    Observation observationToSave = observationMapper
        .toEntityFromCreateObservationRequestDto(observationRequestDto);
    Observation savedObservation = repository.save(observationToSave);

    Set<Long> observedBirdsIds = observationRequestDto.observedBirds()
            .stream().map(IndividualBirdRequestDto::speciesId).collect(Collectors.toSet());
    findSpeciesById(observedBirdsIds);

    List<IndividualBird> birdsToSave = observationRequestDto.observedBirds().stream().map(
            individualBirdMapper::toEntityFromCreateIndividualBird).toList();
    birdsToSave.forEach(bird -> bird.setObservation(observationToSave));
    individualBirdRepository.saveAll(birdsToSave);
    savedObservation.setBirds(birdsToSave);
    return observationMapper.toObservationDto(savedObservation);
  }

  @Override
  @Transactional
  public ObservationDto updateObservation(Long id,
      CreateObservationRequestDto observationRequestDto) {
    Observation observationToEdit = findObservationById(id);
    List<IndividualBirdRequestDto> individualBirdDtos = observationRequestDto.observedBirds();
    Set<Long> observedBirdsSpeciesIds = individualBirdDtos.stream().map(IndividualBirdRequestDto::speciesId).collect(Collectors.toSet());
    findSpeciesById(observedBirdsSpeciesIds);

    List<Long> birdsToEditIds = individualBirdDtos.stream().map(IndividualBirdRequestDto::individualBirdId).toList();
    List<IndividualBird> birds = observationToEdit.getBirds();

    List<IndividualBird> birdsToRemove = birds.stream().filter(bird -> !birdsToEditIds.contains(bird.getIndividualBirdId())).toList();
    birds.removeAll(birdsToRemove);

    List<IndividualBird> birdsToEdit = birds.stream().filter(
            bird -> birdsToEditIds.contains(bird.getIndividualBirdId())).toList();
    Map<Long, IndividualBirdRequestDto> dtoById = individualBirdDtos.stream().filter(dto -> dto.individualBirdId() != null)
            .collect(Collectors.toMap(IndividualBirdRequestDto::individualBirdId, Function.identity()));
    birdsToEdit.forEach(birdToEdit ->
              individualBirdMapper.updateEntityFromDto(dtoById.get(birdToEdit.getIndividualBirdId()), birdToEdit));


    List<IndividualBirdRequestDto> birdsToSaveDtos = observationRequestDto.observedBirds().stream().filter(bird ->
            bird.individualBirdId() == null).toList();
    List<IndividualBird> birdsToSave = birdsToSaveDtos.stream().map(individualBirdMapper::toEntityFromCreateIndividualBird).toList();
    birdsToSave.forEach(bird -> bird.setObservation(observationToEdit));
    birds.addAll(birdsToSave);

    observationToEdit.setLongitude(observationRequestDto.longitude());
    observationToEdit.setLatitude(observationRequestDto.latitude());
    observationToEdit.setDate(observationRequestDto.date());
    Observation editedObservation = repository.save(observationToEdit);
    return observationMapper.toObservationDto(editedObservation);
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

  private void findSpeciesById(Set<Long> ids) {
    Set<Long> existingIds = speciesRepository.findAllById(ids).stream().map(Species::getSpeciesId).collect(Collectors.toSet());
    ids.removeAll(existingIds);
    if(!ids.isEmpty()) {
     logger.info("Species with an id {} not found!", ids);
     throw new ResourceNotFoundException("Species with an id " + ids + " not found");
   }
  }

  private Double haversineFormula(Double latObs, Double lonObs, Double latCenter, Double lonCenter) {

    double latObsRadians = Math.toRadians(latObs);
    Double lonObsRadians = Math.toRadians(lonObs);
    Long EARTH_RADIUS = 6371L;
    double latCenterRadians = Math.toRadians(latCenter);
    Double lonCenterRadians = Math.toRadians(lonCenter);

    double a = Math.pow(Math.sin((latCenterRadians -latObsRadians)/2), 2) + Math.cos(latObsRadians) * Math.cos(latCenterRadians)
            * Math.pow(Math.sin((lonCenterRadians - lonObsRadians)/2), 2);

    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    return EARTH_RADIUS * c;
  }
}