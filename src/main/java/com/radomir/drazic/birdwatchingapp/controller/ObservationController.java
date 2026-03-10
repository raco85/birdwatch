package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.request.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.ObservationDateRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.request.ObservationRadiusFilterRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import com.radomir.drazic.birdwatchingapp.service.IObservationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/observations")
@RequiredArgsConstructor
public class ObservationController {

  private final IObservationService service;

  @GetMapping
  public ResponseEntity<List<ObservationDto>> getAllObservations() {
    List<ObservationDto> observations = service.getAllObservations();

    return ResponseEntity.status(HttpStatus.OK).body(observations);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ObservationDto> getObservationById(@PathVariable Long id) {
    ObservationDto observation = service.getObservationById(id);

    return ResponseEntity.status(HttpStatus.OK).body(observation);
  }

  @GetMapping("/observer/{observerId}")
  public ResponseEntity<List<ObservationDto>> getObservationsByObserver(@PathVariable Long observerId) {
    List<ObservationDto> observations = service.getAllObservationsByUser(observerId);

    return ResponseEntity.status(HttpStatus.OK).body(observations);
  }

  @PostMapping("/observer/radius")
  public ResponseEntity<List<ObservationDto>> getObservationsByObserver(@RequestBody ObservationRadiusFilterRequestDto request) {
    List<ObservationDto> observations = service.getAllObservationsByRadius(request);

    return ResponseEntity.status(HttpStatus.OK).body(observations);
  }

  @GetMapping("/date")
  public ResponseEntity<List<ObservationDto>> getObservationsByDate(@RequestBody ObservationDateRequestDto request) {
    List<ObservationDto> observations = service.getAllObservationsByDate(request);

    return ResponseEntity.status(HttpStatus.OK).body(observations);
  }

  @PostMapping
  public ResponseEntity<ObservationDto> createObservation(@Valid @RequestBody CreateObservationRequestDto observationRequestDto) {
    ObservationDto observation = service.createObservation(observationRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(observation);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ObservationDto> updateObservation(@Valid @PathVariable Long id, @RequestBody CreateObservationRequestDto observationRequestDto){
    ObservationDto observation = service.updateObservation(id, observationRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(observation);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteObservation(@PathVariable Long id) {
    service.deleteObservation(id);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}