package com.sinaukoding.event_booking_system.repository.master;

import com.sinaukoding.event_booking_system.entity.master.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingRepository extends JpaRepository<Booking, String>, JpaSpecificationExecutor<Booking> {

}
