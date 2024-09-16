package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateFamilyRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.FamilyDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IFamilyService {
  List<FamilyDto> getAllFamilies();
  FamilyDto getFamilyById(Long id);
  List<FamilyDto> getFamiliesByName(String name);
  List<FamilyDto> getFamiliesByLatinName(String latinName);
  List<FamilyDto> getAllFamiliesByOrderId(Long orderId);
  FamilyDto createFamily(CreateFamilyRequestDto familyRequestDto);
  FamilyDto updateFamily(Long id, CreateFamilyRequestDto familyRequestDto);
  void deleteFamily(Long id);
}