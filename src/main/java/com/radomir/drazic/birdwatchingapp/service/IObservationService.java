package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.request.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.IndividualBirdStatsRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.ObservationDateRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.ObservationRadiusFilterRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.IndividualBirdsStatsDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IObservationService {

 List<ObservationDto> getAllObservations();
 ObservationDto getObservationById(Long id);
 List<ObservationDto> getAllObservationsByUser(Long observerId);
 List<ObservationDto> getAllObservationsByRadius(ObservationRadiusFilterRequestDto radiusFilterRequestDto);
 List<ObservationDto> getAllObservationsByDate(ObservationDateRequestDto observationDateRequestDto);
 IndividualBirdsStatsDto getBirdStatsInAGivenRadius(IndividualBirdStatsRequestDto statsRequestDto);
 ObservationDto createObservation(CreateObservationRequestDto observationRequestDto);
 ObservationDto updateObservation(Long id, CreateObservationRequestDto observationRequestDto);
 void deleteObservation(Long id);
}