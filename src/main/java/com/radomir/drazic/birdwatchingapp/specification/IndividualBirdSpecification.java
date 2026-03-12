package com.radomir.drazic.birdwatchingapp.specification;

import com.radomir.drazic.birdwatchingapp.entity.IndividualBird;
import com.radomir.drazic.birdwatchingapp.entity.enums.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

@AllArgsConstructor
public class IndividualBirdSpecification {

    private Gender gender;
    private AgeClass ageClass;
    private BreedingStatus breedingStatus;
    private Behaviour behaviour;
    private Habitat habitat;
    private SocialContext socialContext;
    private MigratoryStatus  migratoryStatus;
    private PhysicalCondition  physicalCondition;
    private Long observationId;
    private Long speciesId;

    public static Specification<IndividualBird> byGender(Gender gender) {
        if(gender == null) return null;
        return (root, query, cb) -> cb.equal(root.get("gender"), gender);
    }

    public static Specification<IndividualBird> byAgeClass(AgeClass ageClass) {
        if(ageClass == null) return null;
        return (root, query, cb) -> cb.equal(root.get("ageClass"), ageClass);
    }

    public static Specification<IndividualBird> byBreedingStatus(BreedingStatus breedingStatus) {
        if(breedingStatus == null) return null;
        return (root, query, cb) -> cb.equal(root.get("breedingStatus"), breedingStatus);
    }

    public static Specification<IndividualBird> byBehaviour(Behaviour behaviour) {
        if(behaviour == null) return null;
        return (root, query, cb) -> cb.equal(root.get("behaviour"), behaviour);
    }

    public static Specification<IndividualBird> byHabitat(Habitat habitat) {
        if(habitat == null) return null;
        return (root, query, cb) -> cb.equal(root.get("habitat"), habitat);
    }

    public static Specification<IndividualBird> bySocialContext(SocialContext socialContext) {
        if(socialContext == null) return null;
        return (root, query, cb) -> cb.equal(root.get("socialContext"), socialContext);
    }

    public static Specification<IndividualBird> byMigratoryStatus(MigratoryStatus migratoryStatus) {
        if(migratoryStatus == null) return null;
        return (root, query, cb) -> cb.equal(root.get("migratoryStatus"), migratoryStatus);
    }

    public static Specification<IndividualBird> byPhysicalCondition(PhysicalCondition physicalCondition) {
        if(physicalCondition == null) return null;
        return (root, query, cb) -> cb.equal(root.get("physicalCondition"), physicalCondition);
    }

    public static Specification<IndividualBird> byObservation(Long observationId) {
        return (root, query, cb) -> cb.equal(root.get("observationId"), observationId);
    }

    public static Specification<IndividualBird> bySpecies(Long speciesId) {
        return (root, query, cb) -> cb.equal(root.get("species").get("speciesId"), speciesId);
    }

    public static Specification<IndividualBird> byDateBetween(Date startDate, Date endDate) {
        if(startDate == null || endDate == null) return null;
        return (root, query, cb) -> cb.between(root.get("observation").get("date"), startDate, endDate);
    }
}
