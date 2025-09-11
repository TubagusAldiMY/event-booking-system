package com.sinaukoding.event_booking_system.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    EVENT_ORGANIZER("Event Organizer"),
    PESERTA("Peserta"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }

}
