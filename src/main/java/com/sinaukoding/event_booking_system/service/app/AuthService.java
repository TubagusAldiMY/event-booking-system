package com.sinaukoding.event_booking_system.service.app;

import com.sinaukoding.event_booking_system.entity.managementuser.User;
import com.sinaukoding.event_booking_system.model.app.SimpleMap;
import com.sinaukoding.event_booking_system.model.request.LoginRequestRecord;

public interface AuthService {

    SimpleMap login(LoginRequestRecord request);

    void logout(User userLoggedIn);

}
