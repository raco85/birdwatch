package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateGenusRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.GenusDto;
import com.radomir.drazic.birdwatchingapp.entity.Family;
import com.radomir.drazic.birdwatchingapp.entity.Genus;
import com.radomir.drazic.birdwatchingapp.entity.Order;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.GenusMapper;
import com.radomir.drazic.birdwatchingapp.repository.FamilyRepository;
import com.radomir.drazic.birdwatchingapp.repository.GenusRepository;
import com.radomir.drazic.birdwatchingapp.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenusServiceImpl implements IGenusService{
  private static final Logger logger = LoggerFactory.getLogger(GenusServiceImpl.class);
  private final GenusRepository repository;
  private final FamilyRepository familyRepository;
  private final OrderRepository orderRepository;
  private final GenusMapper mapper;

  @Override
  public List<GenusDto> getAllGeneses() {
    List<Genus> geneses = repository.findAll();
    return geneses.stream().map(mapper::toGenesesDto).collect(
        Collectors.toList());
  }

  @Override
  public GenusDto getGenusById(Long id) {
    Genus genus = findGenusById(id);
    return mapper.toGenesesDto(genus);
  }

  @Override
  public List<GenusDto> getGenesesByName(String name) {
    List<Genus> geneses = repository.findGenesesByName(name);
    return geneses.stream().map(mapper::toGenesesDto).collect(Collectors.toList());
  }

  @Override
  public List<GenusDto> getGenesesByLatinName(String latinName) {
    List<Genus> geneses = repository.findGenesesByLatinName(latinName);
    return geneses.stream().map(mapper::toGenesesDto).collect(Collectors.toList());
  }

  @Override
  public List<GenusDto> getAllGenesesByFamilyId(Long familyId) {
    Family family = findFamilyById(familyId);
    List<Genus> geneses = repository.findAllByFamilyFamilyId(familyId);
    if(geneses.isEmpty())
      logger.info("There's no geneses for {} family", family.getName());
    return geneses.stream().map(mapper::toGenesesDto).collect(Collectors.toList());
  }

  @Override
  public List<GenusDto> getAllGenesesByOrderId(Long orderId) {
    Order order = findOrderById(orderId);
    List<Genus> geneses = repository.findAllByFamilyOrderOrderId(orderId);
    if(geneses.isEmpty())
      logger.info("There's no geneses for {} order", order.getName());
    return geneses.stream().map(mapper::toGenesesDto).collect(Collectors.toList());
  }

  @Override
  public GenusDto createGenus(CreateGenusRequestDto genusRequestDto) {
    Genus genusToSave = mapper.toEntityFromCreateGenusDto(genusRequestDto);
    Family family = findFamilyById(genusRequestDto.familyId());
    Genus savedGenus = repository.save(genusToSave);
    savedGenus.setFamily(family);
    return mapper.toGenesesDto(savedGenus);
  }

  @Override
  public GenusDto updateGenus(Long id, CreateGenusRequestDto genusRequestDto) {
    Genus genusToUpdate = findGenusById(id);
    Family family = findFamilyById(genusRequestDto.familyId());
    genusToUpdate.setName(genusRequestDto.name());
    genusToUpdate.setLatinName(genusRequestDto.latinName());
    genusToUpdate.setFamily(family);
    Genus updatedGenus = repository.save(genusToUpdate);
    return mapper.toGenesesDto(updatedGenus);
  }

  @Override
  @Transactional
  public void deleteGenus(Long id) {
    findGenusById(id);
    repository.deleteById(id);
  }

  private Genus findGenusById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> {
          logger.info("Genus with an id {} not found!", id);
          return new ResourceNotFoundException("Genus with id " + id + " not found!");
        }
    );
  }

  private Family findFamilyById(Long id) {
    return familyRepository.findById(id).orElseThrow(
        () -> {
          logger.info("Family with an id {} not found!", id);
          return new ResourceNotFoundException("Family with id " + id + " not found!");
        }
    );
  }

  private Order findOrderById(Long id) {
    return orderRepository.findById(id).orElseThrow(
        () -> {
          logger.info("Order with an id {} not found!", id);
          return new ResourceNotFoundException("Order with id " + id + " not found!");
        }
    );
  }
}