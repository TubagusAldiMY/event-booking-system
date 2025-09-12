package com.sinaukoding.event_booking_system;

import com.sinaukoding.event_booking_system.entity.master.Event;
import com.sinaukoding.event_booking_system.mapper.master.EventMapper;
import com.sinaukoding.event_booking_system.model.enums.StatusAcara; // Ganti ke StatusAcara
import com.sinaukoding.event_booking_system.model.request.EventRequestRecord;
import com.sinaukoding.event_booking_system.repository.master.EventRepository;
import com.sinaukoding.event_booking_system.service.app.ValidatorService;
import com.sinaukoding.event_booking_system.service.master.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void testAddEvent_Success() {
        Set<String> listImage = new HashSet<>();
        listImage.add("path/to/event_poster.jpg");

        var request = new EventRequestRecord(
                null,
                "KONSER MUSIK ROCK",
                "Konser musik rock tahunan.",
                LocalDateTime.of(2025, 12, 20, 19, 0),
                LocalDateTime.of(2025, 12, 20, 23, 0),
                500000.0,
                1000,
                StatusAcara.DIBUKA,
                "Stadion Utama Gelora Bung Karno",
                listImage
        );


        eventService.add(request);
    }
}