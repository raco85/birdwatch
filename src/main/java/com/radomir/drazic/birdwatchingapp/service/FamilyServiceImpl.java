package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateFamilyRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.FamilyDto;
import com.radomir.drazic.birdwatchingapp.entity.Family;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.FamilyMapper;
import com.radomir.drazic.birdwatchingapp.repository.FamilyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements IFamilyService{
  private static final Logger logger = LoggerFactory.getLogger(FamilyServiceImpl.class);
  private final FamilyRepository repository;
  private final FamilyMapper mapper;
  @Override
  public List<FamilyDto> getAllFamilies() {
    List<Family> families = repository.findAll();
    return families.stream().map(mapper::toFamilyDto).collect(
        Collectors.toList());
  }

  @Override
  public FamilyDto getFamilyById(Long id) {
    Family family = findFamilyById(id);
    return mapper.toFamilyDto(family);
  }

  @Override
  public FamilyDto createFamily(CreateFamilyRequestDto familyRequestDto) {
    Family familyToSave = mapper.toEntityFromCreateFamilyRequestDto(familyRequestDto);
    Family savedFamily = repository.save(familyToSave);
    return mapper.toFamilyDto(savedFamily);
  }

  @Override
  public FamilyDto updateFamily(Long id, CreateFamilyRequestDto familyRequestDto) {
    Family familyToEdit = findFamilyById(id);
    familyToEdit.setName(familyRequestDto.name());
    familyToEdit.setLatinName(familyRequestDto.latinName());
    Family editedFamily = repository.save(familyToEdit);
    return mapper.toFamilyDto(editedFamily);
  }

  @Override
  public void deleteFamily(Long id) {
    repository.deleteById(id);
  }

  private Family findFamilyById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> {
          logger.info("Family with an id {} not found!", id);
          return new ResourceNotFoundException("Family with id " + id + " not found!");
        }
    );
  }
}