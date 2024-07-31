package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.Genus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenusRepository extends JpaRepository<Genus, Long> {

}