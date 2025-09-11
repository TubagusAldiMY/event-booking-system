package com.sinaukoding.event_booking_system.model.enums;

public enum StatusPembayaran {

    PENDING("Pending"),
    LUNAS("Lunas"),
    BATAL("Batal");

    private final String label;

    StatusPembayaran(String label) {
        this.label = label;
    }
}