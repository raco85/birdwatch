package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

}