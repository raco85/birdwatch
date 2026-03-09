package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum BreedingStatus implements LabeledEnum {
    NOT_BREEDING("Not breeding"), POSSIBLE_BREEDER("Possible breeder"),
    PROBABLE_BREEDER("Probable breeder"), CONFIRMED_BREEDER("Confirmed breeder"),
    NEST_BUILDING("Nest building"), INCUBATING("Incubating"),
    FEEDING_YOUNG("Feeding young"), UNKNOWN("Unknown");

    public final String label;

    BreedingStatus(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static BreedingStatus getByLabel(String label) {
        for (BreedingStatus breedingStatus : values()) {
            if (breedingStatus.label.equalsIgnoreCase(label)) {
                return breedingStatus;
            }
        }
        return null;
    }
}