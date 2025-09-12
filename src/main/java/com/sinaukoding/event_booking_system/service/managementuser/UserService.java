package com.sinaukoding.event_booking_system.service.managementuser;

import com.sinaukoding.event_booking_system.model.app.SimpleMap;
import com.sinaukoding.event_booking_system.model.filter.UserFilterRecord;
import com.sinaukoding.event_booking_system.model.request.RegisterRequestRecord;
import com.sinaukoding.event_booking_system.model.request.UserRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void add(UserRequestRecord request);

    void edit(UserRequestRecord request);

    Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

    void register(RegisterRequestRecord request);

}