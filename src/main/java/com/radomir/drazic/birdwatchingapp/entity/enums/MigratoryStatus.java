package com.radomir.drazic.birdwatchingapp.entity.enums;

public enum MigratoryStatus {
    RESIDENT("Resident"), MIGRANT("Migrant"), PASSAGE("Passage"), WINTERING("Wintering"), UNKNOWN("Unknown");


    public final String label;

    MigratoryStatus(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    public static MigratoryStatus getByLabel(String label) {
        for (MigratoryStatus status : values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        return null;
    }
}