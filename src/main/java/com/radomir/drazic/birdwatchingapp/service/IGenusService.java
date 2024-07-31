package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateGenusRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.GenusDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IGenusService {
  List<GenusDto> getAllGeneses();
  GenusDto getGenusById(Long id);
  GenusDto createGenus(CreateGenusRequestDto genusRequestDto);
  GenusDto updateGenus(Long id, CreateGenusRequestDto genusRequestDto);
  void deleteGenus(Long id);
}