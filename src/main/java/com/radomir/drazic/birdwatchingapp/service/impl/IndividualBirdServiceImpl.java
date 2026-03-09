package com.radomir.drazic.birdwatchingapp.service.impl;

import com.radomir.drazic.birdwatchingapp.dto.request.IndividualBirdRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.IndividualBirdDto;
import com.radomir.drazic.birdwatchingapp.entity.IndividualBird;
import com.radomir.drazic.birdwatchingapp.entity.enums.*;
import com.radomir.drazic.birdwatchingapp.mapper.IndividualBirdMapper;
import com.radomir.drazic.birdwatchingapp.repository.IndividualBirdRepository;
import com.radomir.drazic.birdwatchingapp.service.IndividualBirdService;
import com.radomir.drazic.birdwatchingapp.specification.IndividualBirdSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualBirdServiceImpl implements IndividualBirdService {

    private final IndividualBirdRepository individualBirdRepository;
    private final IndividualBirdMapper mapper;


    @Override
    public List<IndividualBirdDto> searchIndividualBirds(IndividualBirdRequestDto request) {

        Specification<IndividualBird> spec = Specification
                .where(IndividualBirdSpecification.byGender(Gender.getByLabel(request.gender())))
                .and(IndividualBirdSpecification.byAgeClass(AgeClass.getByLabel(request.ageClass())))
                .and(IndividualBirdSpecification.byBreedingStatus(BreedingStatus.getByLabel(request.breedingStatus())))
                .and(IndividualBirdSpecification.byBehaviour(Behaviour.getByLabel(request.behaviour())))
                .and(IndividualBirdSpecification.byHabitat(Habitat.getByLabel(request.habitat())))
                .and(IndividualBirdSpecification.bySocialContext(SocialContext.getByLabel(request.socialContext())))
                .and(IndividualBirdSpecification.byMigratoryStatus(MigratoryStatus.getByLabel(request.migratoryStatus())))
                .and(IndividualBirdSpecification.byPhysicalCondition(PhysicalCondition.getByLabel(request.physicalCondition())))
                .and(IndividualBirdSpecification.bySpecies(request.speciesId()));
        List<IndividualBird> individualBirds = individualBirdRepository.findAll(spec);

        return individualBirds.stream().map(mapper::toIndividualBirdDto).toList();
    }
}