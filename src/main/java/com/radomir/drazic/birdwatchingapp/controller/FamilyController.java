package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.CreateFamilyRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.FamilyDto;
import com.radomir.drazic.birdwatchingapp.service.IFamilyService;
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

@RestController
@RequestMapping("/families")
@RequiredArgsConstructor
public class FamilyController {

  private final IFamilyService service;

  @GetMapping
  public ResponseEntity<List<FamilyDto>> getAllFamilies(){
    List<FamilyDto> familyDtos = service.getAllFamilies();
    return ResponseEntity.status(HttpStatus.OK).body(familyDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FamilyDto> getFamilyById(@PathVariable Long id) {
    FamilyDto familyDto = service.getFamilyById(id);

    return ResponseEntity.status(HttpStatus.OK).body(familyDto);
  }

  @PostMapping
  public ResponseEntity<FamilyDto> createFamily(@RequestBody CreateFamilyRequestDto familyRequestDto) {
    FamilyDto familyDto = service.createFamily(familyRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(familyDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FamilyDto> updateFamily(@PathVariable Long id, @RequestBody CreateFamilyRequestDto familyRequestDto) {
    FamilyDto familyDto = service.updateFamily(id, familyRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(familyDto);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteFamily(@PathVariable Long id) {
    service.deleteFamily(id);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}