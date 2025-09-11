package com.sinaukoding.event_booking_system.repository.master;

import com.sinaukoding.event_booking_system.entity.master.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, String>, JpaSpecificationExecutor<Event> {

}

