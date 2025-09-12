package com.sinaukoding.event_booking_system.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestRecord(
        @NotBlank(message = "Nama tidak boleh kosong")
        String nama,

        @NotBlank(message = "Username tidak boleh kosong")
        @Size(min = 4, message = "Username minimal 4 karakter")
        String username,

        @NotBlank(message = "Email tidak boleh kosong")
        @Email(message = "Format email tidak valid")
        String email,

        @NotBlank(message = "Password tidak boleh kosong")
        @Size(min = 6, message = "Password minimal 6 karakter")
        String password
) {}