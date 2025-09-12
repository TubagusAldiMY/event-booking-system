package com.sinaukoding.event_booking_system.model.filter;

import com.sinaukoding.event_booking_system.model.enums.Role;
import com.sinaukoding.event_booking_system.model.enums.Status;

public record UserFilterRecord(String nama,
                               String email,
                               String username,
                               Status status,
                               Role role) {
}