package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IObservationService {

 List<ObservationDto> getAllObservations();
 ObservationDto getObservationById(Long id);
 List<ObservationDto> getAllObservationsBySpecies(Long speciesId);
 List<ObservationDto> getAllObservationsByUser(Long observerId);
 ObservationDto createObservation(CreateObservationRequestDto observationRequestDto);
 ObservationDto updateObservation(Long id, CreateObservationRequestDto observationRequestDto);
 void deleteObservation(Long id);
}