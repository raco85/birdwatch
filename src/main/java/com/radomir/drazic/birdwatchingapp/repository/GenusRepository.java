package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.Genus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenusRepository extends JpaRepository<Genus, Long> {

  @Query("SELECT g FROM Genus g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :searchName, '%'))")
  List<Genus> findGenesesByName(String searchName);

  @Query("SELECT g FROM Genus g WHERE LOWER(g.latinName) LIKE LOWER(CONCAT('%', :latinName, '%'))")
  List<Genus> findGenesesByLatinName(String latinName);

  List<Genus> findAllByFamilyFamilyId(Long familyId);

  List<Genus> findAllByFamilyOrderOrderId(Long orderId);
}