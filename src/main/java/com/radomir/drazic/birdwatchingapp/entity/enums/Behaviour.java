package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum Behaviour implements LabeledEnum {
    FLYING("Flying"), SOARING("Soaring") ,FORAGING("Foraging"), SINGING("Singing"),
    CALLING("Calling"), HUNTING("Hunting"), PERCHING("Perching"), NEST_BUILDING("Nest building"),
    INCUBATING("Incubating"), FEEDING_YOUNG("Feeding young"), RESTING("Resting"),
    AGGRESSIVE("Aggressive"), DISPLAYING("Displaying"), UNKNOWN("Unknown");


    public final String label;

    Behaviour(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static Behaviour getByLabel(String label) {
        for (Behaviour behaviour : values()) {
            if (behaviour.label.equalsIgnoreCase(label)) {
                return behaviour;
            }
        }
        return null;
    }
}