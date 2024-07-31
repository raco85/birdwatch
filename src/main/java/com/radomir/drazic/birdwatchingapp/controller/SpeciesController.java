package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.CreateSpeciesRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.SpeciesDto;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import com.radomir.drazic.birdwatchingapp.service.ISpeciesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/species")
@RestController
@RequiredArgsConstructor
public class SpeciesController {

  private final ISpeciesService service;

  @GetMapping
  public ResponseEntity<List<SpeciesDto>> getAllSpecies() {
    List<SpeciesDto> species = service.getAllSpecies();

    return ResponseEntity.status(HttpStatus.OK).body(species);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SpeciesDto> getSpeciesById(@PathVariable Long id) {
    SpeciesDto species = service.getSpeciesById(id);

    return ResponseEntity.status(HttpStatus.OK).body(species);
  }

  @PostMapping
  public ResponseEntity<SpeciesDto> createSpecies(@RequestBody CreateSpeciesRequestDto speciesRequestDto) {
    SpeciesDto species = service.createSpecies(speciesRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(species);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SpeciesDto> updateSpecies(@PathVariable Long id, @RequestBody CreateSpeciesRequestDto speciesRequestDto) {
    SpeciesDto species = service.updateSpecies(id, speciesRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(species);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSpeciesById(@PathVariable Long id) {
    service.deleteSpeciesById(id);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
