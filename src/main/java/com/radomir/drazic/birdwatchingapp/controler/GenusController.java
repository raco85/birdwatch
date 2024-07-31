package com.radomir.drazic.birdwatchingapp.controler;

import com.radomir.drazic.birdwatchingapp.dto.CreateGenusRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.GenusDto;
import com.radomir.drazic.birdwatchingapp.service.IGenusService;
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
@RequestMapping("/geneses")
@RequiredArgsConstructor
public class GenusController {

  private final IGenusService service;

  @GetMapping
  public ResponseEntity<List<GenusDto>> getAllGeneses() {
    List<GenusDto> genusDtos = service.getAllGeneses();

    return ResponseEntity.status(HttpStatus.OK).body(genusDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GenusDto> getGenusById(@PathVariable Long id) {
    GenusDto genusDto = service.getGenusById(id);

    return ResponseEntity.status(HttpStatus.OK).body(genusDto);
  }

  @PostMapping
  public ResponseEntity<GenusDto> createGenus(@RequestBody CreateGenusRequestDto genusRequestDto) {
    GenusDto genusDto = service.createGenus(genusRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(genusDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenusDto> updateGenus(@PathVariable Long id, @RequestBody CreateGenusRequestDto genusRequestDto) {
    GenusDto genusDto = service.updateGenus(id, genusRequestDto);

    return ResponseEntity.status(HttpStatus.OK).body(genusDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGenus(@PathVariable Long id) {
    service.deleteGenus(id);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}