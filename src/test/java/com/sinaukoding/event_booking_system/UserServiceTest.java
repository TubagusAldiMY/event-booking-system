package com.sinaukoding.event_booking_system;

import com.sinaukoding.event_booking_system.model.enums.Role;
import com.sinaukoding.event_booking_system.model.enums.Status;
import com.sinaukoding.event_booking_system.model.request.UserRequestRecord;
import com.sinaukoding.event_booking_system.service.managementuser.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void addUserTest() {
        UserRequestRecord request = new UserRequestRecord(null,
                "Tubagus Aldi",
                "TubsAMY",
                "tubsamy@yopmail.com",
                "TubsAMY123",
                Status.AKTIF,
                Role.EVENT_ORGANIZER
        );
        userService.add(request);
    }

}
