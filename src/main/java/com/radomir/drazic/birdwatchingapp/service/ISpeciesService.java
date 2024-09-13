package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateSpeciesRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.SpeciesDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ISpeciesService {
  List<SpeciesDto> getAllSpecies();
  SpeciesDto getSpeciesById(Long id);
  List<SpeciesDto> getSpeciesByName(String name);
  List<SpeciesDto> getSpeciesByLatinName(String latinName);
  List<SpeciesDto> getAllSpeciesByGenusId(Long genusId);
  List<SpeciesDto> getAllSpeciesByFamilyId(Long familyId);
  List<SpeciesDto> getAllSpeciesByOrderId(Long orderId);
  SpeciesDto createSpecies(CreateSpeciesRequestDto speciesRequestDto);
  SpeciesDto updateSpecies(Long id, CreateSpeciesRequestDto speciesRequestDto);
  void deleteSpeciesById(Long id);

}