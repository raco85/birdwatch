package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.request.IndividualBirdRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.IndividualBirdDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndividualBirdService {

    List<IndividualBirdDto> searchIndividualBirds(IndividualBirdRequestDto  individualBirdRequestDto);
}