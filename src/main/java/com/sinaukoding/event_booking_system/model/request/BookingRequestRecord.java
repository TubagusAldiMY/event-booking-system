//package com.sinaukoding.event_booking_system.model.request;
//
//import com.sinaukoding.event_booking_system.model.enums.StatusPembayaran;
//import jakarta.validation.constraints.NotNull;
//
//import java.time.LocalDateTime;
//
//public record BookingRequestRecord(
//        @NotNull(message = "ID Acara tidak boleh kosong") String eventId,
//        @NotNull(message = "Jumlah tiket tidak boleh kosong") Integer jumlahTiket
//) {}