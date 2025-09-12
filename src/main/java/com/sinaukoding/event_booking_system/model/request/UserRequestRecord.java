package com.sinaukoding.event_booking_system.model.request;

import com.sinaukoding.event_booking_system.model.enums.Role;
import com.sinaukoding.event_booking_system.model.enums.Status;

public record UserRequestRecord(String id,
                                String nama,
                                String username,
                                String email,
                                String password,
                                Status status,
                                Role role) {
}

