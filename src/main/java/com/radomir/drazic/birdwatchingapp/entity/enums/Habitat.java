package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum Habitat {
    FOREST("Forest"), URBAN("Urban"), AGRICULTURAL("Agricultural"), WETLAND("Wetland"), RIVER("River"), LAKE("Lake"),
    COASTAL("Coastal"), GRASSLAND("Grassland"), MOUNTAIN("Mountain"), UNKNOWN("Unknown");



    public final String label;

    Habitat(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    public static Habitat getByLabel(String label) {
        for (Habitat habitat : values()) {
            if (habitat.label.equalsIgnoreCase(label)) {
                return habitat;
            }
        }
        return null;
    }
}