package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateSpeciesRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.SpeciesDto;
import com.radomir.drazic.birdwatchingapp.entity.Family;
import com.radomir.drazic.birdwatchingapp.entity.Genus;
import com.radomir.drazic.birdwatchingapp.entity.Order;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.SpeciesMapper;
import com.radomir.drazic.birdwatchingapp.repository.FamilyRepository;
import com.radomir.drazic.birdwatchingapp.repository.GenusRepository;
import com.radomir.drazic.birdwatchingapp.repository.OrderRepository;
import com.radomir.drazic.birdwatchingapp.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements ISpeciesService {
  private static final Logger logger = LoggerFactory.getLogger(SpeciesServiceImpl.class);
  private final SpeciesRepository repository;
  private final GenusRepository genusRepository;
  private final FamilyRepository familyRepository;
  private final OrderRepository orderRepository;
  private final SpeciesMapper mapper;

  @Override
  public List<SpeciesDto> getAllSpecies() {
    List<Species> species = repository.findAll();
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public SpeciesDto getSpeciesById(Long id) {
    Species species = findSpeciesById(id);
    return mapper.toSpeciesDto(species);
  }

  @Override
  public List<SpeciesDto> getSpeciesByName(String name) {
    List<Species> species = repository.findSpeciesByName(name);
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public List<SpeciesDto> getSpeciesByLatinName(String latinName) {
    List<Species> species = repository.findSpeciesByLatinName(latinName);
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }
  @Override
  public List<SpeciesDto> getAllSpeciesByGenusId(Long genusId) {
    Genus genus = findGenusById(genusId);
    List<Species> species = repository.findAllByGenusGenusId(genusId);
    if(species.isEmpty())
      logger.info("There's no species for {} genus", genus.getName());
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public List<SpeciesDto> getAllSpeciesByFamilyId(Long familyId) {
    Family family = findFamilyById(familyId);
    List<Species> species = repository.findAllByGenusFamilyFamilyId(familyId);
    if(species.isEmpty())
      logger.info("There's no species for {} family", family.getName());
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public List<SpeciesDto> getAllSpeciesByOrderId(Long orderId) {
    Order order = findOrderById(orderId);
    List<Species> species = repository.findAllByGenusFamilyOrderOrderId(orderId);
    if(species.isEmpty())
      logger.info("There's no species for {} order", order.getName());
    return species.stream().map(mapper::toSpeciesDto).collect(Collectors.toList());
  }

  @Override
  public SpeciesDto createSpecies(CreateSpeciesRequestDto speciesRequestDto) {
    Species speciesToSave = mapper.toEntityFromCreateSpeciesRequestDto(speciesRequestDto);
    Genus genus = findGenusById(speciesRequestDto.genusId());
    Species savedSpecies = repository.save(speciesToSave);
    savedSpecies.setGenus(genus);
    logger.info("Saved species is: {}", savedSpecies.getName());
    return mapper.toSpeciesDto(savedSpecies);
  }

  @Override
  public SpeciesDto updateSpecies(Long id, CreateSpeciesRequestDto speciesRequestDto) {
    Species species = findSpeciesById(id);
    Genus genus = findGenusById(speciesRequestDto.genusId());
    species.setName(speciesRequestDto.name());
    species.setLatinName(speciesRequestDto.latinName());
    species.setGenus(genus);
    Species updatedSpecies = repository.save(species);
    return mapper.toSpeciesDto(updatedSpecies);
  }

  @Override
  @Transactional
  public void deleteSpeciesById(Long id) {
    findSpeciesById(id);
    repository.deleteById(id);
  }

  private Species findSpeciesById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> {
          logger.info("Species with an id {} not found!", id);
          return new ResourceNotFoundException("Species with id " + id + " not found!");
        }
    );
  }

  private Genus findGenusById(Long id) {
    return genusRepository.findById(id).orElseThrow(
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