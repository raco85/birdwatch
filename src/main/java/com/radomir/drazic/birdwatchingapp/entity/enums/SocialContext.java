package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum SocialContext implements LabeledEnum {
    SOLITARY("Solitary"), PAIR("Pair"), SMALL_GROUP("Small group"),
    LARGE_FLOCK("Large flock"), MIXED_SPECIES_FLOCK("Mixed species flock");


    public final String label;

    SocialContext(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static SocialContext getByLabel(String label) {
        for (SocialContext context : values()) {
            if (context.label.equalsIgnoreCase(label)) {
                return context;
            }
        }
        return null;
    }
}