package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateFamilyRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.FamilyDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IFamilyService {
  List<FamilyDto> getAllFamilies();
  FamilyDto getFamilyById(Long id);
  FamilyDto createFamily(CreateFamilyRequestDto familyRequestDto);
  FamilyDto updateFamily(Long id, CreateFamilyRequestDto familyRequestDto);
  void deleteFamily(Long id);
}