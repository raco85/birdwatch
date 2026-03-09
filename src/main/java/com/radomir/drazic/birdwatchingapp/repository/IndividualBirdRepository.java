package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.IndividualBird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualBirdRepository extends JpaSpecificationExecutor<IndividualBird>, JpaRepository<IndividualBird, Long> {
}