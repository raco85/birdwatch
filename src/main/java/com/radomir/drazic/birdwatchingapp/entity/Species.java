package com.radomir.drazic.birdwatchingapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long speciesId;
  @Column
  private String name;
  @Column
  private String latinName;
  @Column
  private String image;
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "genus_id")
  private Genus genus;
  @OneToMany(mappedBy = "species", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REFRESH)
  private List<Observation> observations;
}