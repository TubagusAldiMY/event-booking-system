package com.sinaukoding.event_booking_system.service.master;

import com.sinaukoding.event_booking_system.model.request.BookingRequestRecord;

public interface BookingService {

    void createBooking(BookingRequestRecord request);
}
