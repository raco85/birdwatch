package com.radomir.drazic.birdwatchingapp.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
  private List<IndividualBird> birds;
}