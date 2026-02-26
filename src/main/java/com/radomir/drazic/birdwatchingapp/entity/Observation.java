package com.radomir.drazic.birdwatchingapp.entity;

import jakarta.persistence.*;

import java.util.Date;
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
public class Observation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long observationId;
  @Column
  private Double longitude;
  @Column
  private Double latitude;
  @Column
  private Date date;
  @OneToMany(mappedBy = "observation", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<IndividualBird> birds;
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "id")
  private User observer;
}