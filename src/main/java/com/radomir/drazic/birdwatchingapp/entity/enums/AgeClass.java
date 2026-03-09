package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum AgeClass implements LabeledEnum {
    NESTLING("Nestling"), FLEDGLING("Fledgling"), JUVENILE("Juvenile"), SUBADULT("Subadult"),
    ADULT("Adult"), UNKNOWN("Unknown");


    public final String label;

    AgeClass(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static AgeClass getByLabel(String label) {
        for (AgeClass ageClass : values()) {
            if (ageClass.label.equalsIgnoreCase(label)) {
                return ageClass;
            }
        }
        return null;
    }
}