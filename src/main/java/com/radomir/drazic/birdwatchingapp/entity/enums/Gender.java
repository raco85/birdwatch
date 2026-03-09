package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum Gender implements LabeledEnum {
    MALE("Male"), FEMALE("Female"), UNKNOWN("Unknown");


    public final String label;

    Gender(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static Gender getByLabel(String label) {
        for (Gender gender : values()) {
            if (gender.label.equalsIgnoreCase(label)) {
                return gender;
            }
        }
        return null;
    }
}