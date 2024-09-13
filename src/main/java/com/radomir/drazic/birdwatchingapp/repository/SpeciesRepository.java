package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.Species;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

  @Query("SELECT s FROM Species s WHERE " +
      "LOWER(s.name) LIKE LOWER(CONCAT('%', :searchName, '%'))")
  List<Species> findSpeciesByName(String searchName);

  @Query("SELECT s FROM Species s WHERE " +
      "LOWER(s.latinName) LIKE LOWER(CONCAT('%', :latinName, '%'))")
  List<Species> findSpeciesByLatinName(String latinName);

  List<Species> findAllByGenusGenusId(Long genusId);

  List<Species> findAllByGenusFamilyFamilyId(Long familyId);

  List<Species> findAllByGenusFamilyOrderOrderId(Long orderId);
}