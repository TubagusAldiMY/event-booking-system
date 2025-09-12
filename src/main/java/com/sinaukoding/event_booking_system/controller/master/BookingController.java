package com.sinaukoding.event_booking_system.controller.master;

import com.sinaukoding.event_booking_system.model.request.BookingRequestRecord;
import com.sinaukoding.event_booking_system.model.response.BaseResponse;
import com.sinaukoding.event_booking_system.service.master.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('PESERTA')")
    public BaseResponse<?> createBooking(@RequestBody BookingRequestRecord request) {
        bookingService.createBooking(request);
        return BaseResponse.ok("Booking berhasil dibuat.", null);
    }
}