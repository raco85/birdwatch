package com.radomir.drazic.birdwatchingapp.entity;

import com.radomir.drazic.birdwatchingapp.entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IndividualBird {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long individualBirdId;
    @Enumerated(EnumType.STRING)
    private AgeClass ageClass;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private BreedingStatus breedingStatus;
    @Enumerated(EnumType.STRING)
    private Behaviour behaviour;
    @Enumerated(EnumType.STRING)
    private Habitat habitat;
    @Enumerated(EnumType.STRING)
    private SocialContext socialContext;
    @Enumerated(EnumType.STRING)
    private MigratoryStatus migratoryStatus;
    @Enumerated(EnumType.STRING)
    private PhysicalCondition physicalCondition;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "observation_id")
    private Observation observation;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private Species species;
}