package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum PhysicalCondition implements LabeledEnum {
    HEALTHY("Healthy"), INJURED("Injured"), SICK("Sick"), DEAD("Dead"), UNKNOWN("Unknown");


    public final String label;

    PhysicalCondition(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static PhysicalCondition getByLabel(String label) {
        for (PhysicalCondition condition : values()) {
            if (condition.label.equalsIgnoreCase(label)) {
                return condition;
            }
        }
        return null;
    }
}