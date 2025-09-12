package com.sinaukoding.event_booking_system.mapper.master;

import com.sinaukoding.event_booking_system.entity.master.Event;
import com.sinaukoding.event_booking_system.entity.master.EventImage;
import com.sinaukoding.event_booking_system.model.request.EventRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventMapper {

    public Event requestToEntity(EventRequestRecord request) {
        Event event = Event.builder()
                .namaAcara(request.namaAcara().toUpperCase())
                .deskripsi(request.deskripsi())
                .tanggalMulai(request.tanggalMulai())
                .tanggalSelesai(request.tanggalSelesai())
                .hargaTiket(request.hargaTiket())
                .kapasitas(request.kapasitas())
                .statusAcara(request.statusAcara())
                .lokasi(request.lokasi())
                .build();

        event.setListImage(request.listImage().stream()
                .map(path -> EventImage.builder()
                        .path(path)
                        .event(event)
                        .build())
                .collect(Collectors.toSet()));

        return event;
    }

}
