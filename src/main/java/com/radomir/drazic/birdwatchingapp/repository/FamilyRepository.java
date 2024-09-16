package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.Family;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

  @Query("SELECT f FROM Family f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :searchName, '%'))")
  List<Family> findFamiliesByName(String searchName);

  @Query("SELECT f FROM Family f WHERE LOWER(f.latinName) LIKE LOWER(CONCAT('%', :latinName, '%'))")
  List<Family> findFamiliesByLatinName(String latinName);

  List<Family> findAllByOrderOrderId(Long orderId);
}