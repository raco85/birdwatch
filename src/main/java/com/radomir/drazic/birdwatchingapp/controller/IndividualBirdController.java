package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.request.IndividualBirdStatsRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.IndividualBirdDto;
import com.radomir.drazic.birdwatchingapp.service.IndividualBirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/individual_birds")
@RequiredArgsConstructor
public class IndividualBirdController {


    private final IndividualBirdService service;


    @PostMapping
    public ResponseEntity<List<IndividualBirdDto>> searchIndividualBirds(@RequestBody IndividualBirdStatsRequestDto request) {
        List<IndividualBirdDto> birds = service.searchIndividualBirds(request);

        return ResponseEntity.status(HttpStatus.OK).body(birds);
    }
}