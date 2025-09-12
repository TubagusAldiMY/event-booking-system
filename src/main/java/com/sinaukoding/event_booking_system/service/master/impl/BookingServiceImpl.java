package com.sinaukoding.event_booking_system.service.master.impl;

import com.sinaukoding.event_booking_system.config.UserLoggedInConfig;
import com.sinaukoding.event_booking_system.entity.master.Booking;
import com.sinaukoding.event_booking_system.entity.master.Event;
import com.sinaukoding.event_booking_system.entity.managementuser.User;
import com.sinaukoding.event_booking_system.model.enums.StatusPembayaran;
import com.sinaukoding.event_booking_system.model.request.BookingRequestRecord;
import com.sinaukoding.event_booking_system.repository.master.BookingRepository;
import com.sinaukoding.event_booking_system.repository.master.EventRepository;
import com.sinaukoding.event_booking_system.repository.managementuser.UserRepository;
import com.sinaukoding.event_booking_system.service.app.ValidatorService;
import com.sinaukoding.event_booking_system.service.master.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;

    @Transactional
    @Override
    public void createBooking(BookingRequestRecord request) {
        log.info("Memulai proses pembuatan booking...");

        validatorService.validator(request);

        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() -> new RuntimeException("Acara tidak ditemukan."));

        User currentUser = getCurrentUser();

        Booking booking = Booking.builder()
                .event(event)
                .user(currentUser)
                .jumlahTiket(request.jumlahTiket())
                .totalHarga(request.jumlahTiket() * event.getHargaTiket())
                .tanggalBooking(LocalDateTime.now())
                .statusPembayaran(StatusPembayaran.PENDING)
                .build();

        bookingRepository.save(booking);
        event.setKapasitas(event.getKapasitas() - request.jumlahTiket());
        eventRepository.save(event);

        log.info("Booking berhasil dibuat.");
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserLoggedInConfig) principal).getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan."));
    }
}