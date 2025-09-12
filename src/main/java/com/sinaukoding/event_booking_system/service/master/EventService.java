package com.sinaukoding.event_booking_system.service.master;

import com.sinaukoding.event_booking_system.model.app.SimpleMap;
import com.sinaukoding.event_booking_system.model.filter.EventFilterRecord;
import com.sinaukoding.event_booking_system.model.request.EventRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    void add(EventRequestRecord request);

    void edit(EventRequestRecord request);

    Page<SimpleMap> findAll(EventFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}

