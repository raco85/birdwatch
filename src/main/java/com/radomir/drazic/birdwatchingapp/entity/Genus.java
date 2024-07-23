package com.radomir.drazic.birdwatchingapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Genus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long genusId;
  private String name;
  private String latinName;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "family_id")
  private Family family;
  @OneToMany(mappedBy = "genus", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Species> species;
}