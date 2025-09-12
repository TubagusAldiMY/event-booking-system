package com.sinaukoding.event_booking_system.service.master.impl;

import com.sinaukoding.event_booking_system.builder.CustomBuilder;
import com.sinaukoding.event_booking_system.builder.CustomSpecification;
import com.sinaukoding.event_booking_system.builder.MultipleCriteria;
import com.sinaukoding.event_booking_system.builder.SearchCriteria;
import com.sinaukoding.event_booking_system.entity.master.Event;
import com.sinaukoding.event_booking_system.entity.master.EventImage;
import com.sinaukoding.event_booking_system.mapper.master.EventMapper;
import com.sinaukoding.event_booking_system.model.app.AppPage;
import com.sinaukoding.event_booking_system.model.app.SimpleMap;
import com.sinaukoding.event_booking_system.model.filter.EventFilterRecord;
import com.sinaukoding.event_booking_system.model.request.EventRequestRecord;
import com.sinaukoding.event_booking_system.repository.master.EventRepository;
import com.sinaukoding.event_booking_system.service.app.ValidatorService;
import com.sinaukoding.event_booking_system.service.master.EventService;
import com.sinaukoding.event_booking_system.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ValidatorService validatorService;
    private final EventMapper eventMapper;

    @Override
    public void add(EventRequestRecord request) {
        try {
            log.trace("Masuk ke menu tambah data event");
            log.debug("Request data event: {}", request);

            // validator mandatory
            validatorService.validator(request);
            log.debug("Validasi dasar berhasil untuk acara: {}", request.namaAcara());

            // 2. Validasi Logika Bisnis Spesifik untuk Acara
            if (request.tanggalMulai().isAfter(request.tanggalSelesai())) {
                log.warn("Validasi gagal: Tanggal mulai tidak boleh setelah tanggal selesai.");
            }

            if (request.kapasitas() <= 0) {
                log.warn("Validasi gagal: Kapasitas acara harus lebih dari 0.");
            }


            var event = eventMapper.requestToEntity(request);
            eventRepository.save(event);

            log.info("Event {} berhasil ditambahkan", request.namaAcara());
            log.trace("Tambah data event berhasil dan selesai");
        } catch (Exception e) {
            log.error("Tambah data event gagal: {}", e.getMessage());
        }
    }

    @Override
    public void edit(EventRequestRecord request) {
        // validator mandatory
        validatorService.validator(request);

        var eventExisting = eventRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Event tidak ditemukan"));
        var event = eventMapper.requestToEntity(request);
        event.setId(eventExisting.getId());
        eventRepository.save(event);
    }

    @Override
    public Page<SimpleMap> findAll(EventFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Event> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("namaAcara", filterRequest.namaAcara(), builder);
        FilterUtil.builderConditionNotBlankLike("lokasi", filterRequest.lokasi(), builder);
        FilterUtil.builderConditionNotNullEqual("statusAcara", filterRequest.statusAcara(), builder);

        if (filterRequest.hargaTiketBawah() != null && filterRequest.hargaTiketAtas() != null) {
            builder.with(MultipleCriteria.builder().criterias(
                    SearchCriteria.OPERATOR_AND,
                    SearchCriteria.of("hargaTiket", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.hargaTiketBawah()),
                    SearchCriteria.of("hargaTiket", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.hargaTiketAtas())
            ));
        } else if (filterRequest.hargaTiketAtas() != null) {
            builder.with("hargaTiket", CustomSpecification.OPERATION_LESS_THAN_EQUAL, filterRequest.hargaTiketAtas());
        } else if (filterRequest.hargaTiketBawah() != null) {
            builder.with("hargaTiket", CustomSpecification.OPERATION_GREATER_THAN_EQUAL, filterRequest.hargaTiketBawah());
        }

        if (filterRequest.tanggalMulai() != null) {
            builder.with("tanggalMulai", CustomSpecification.OPERATION_LOCAL_DATE_GREATER_THAN_EQUAL, filterRequest.tanggalMulai().atStartOfDay());
        }

        if (filterRequest.tanggalSelesai() != null) {
            builder.with("tanggalSelesai", CustomSpecification.OPERATION_LOCAL_DATE_LESS_THAN_EQUAL, filterRequest.tanggalSelesai().plusDays(1).atStartOfDay());
        }

        Page<Event> listEvent = eventRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listEvent.stream().map(event -> {
            SimpleMap data = new SimpleMap();
            data.put("id", event.getId());
            data.put("namaAcara", event.getNamaAcara());
            data.put("deskripsi", event.getDeskripsi());
            data.put("tanggalMulai", event.getTanggalMulai());
            data.put("tanggalSelesai", event.getTanggalSelesai());
            data.put("hargaTiket", event.getHargaTiket());
            data.put("kapasitas", event.getKapasitas());
            data.put("statusAcara", event.getStatusAcara());
            data.put("lokasi", event.getLokasi());
            data.put("createdDate", event.getCreatedDate());
            data.put("modifiedDate", event.getModifiedDate());
            data.put("listImage", event.getListImage().stream().map(EventImage::getPath).collect(Collectors.toSet()));
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listEvent.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event tidak ditemukan"));

        SimpleMap data = new SimpleMap();
        data.put("id", event.getId());
        data.put("namaAcara", event.getNamaAcara());
        data.put("deskripsi", event.getDeskripsi());
        data.put("tanggalMulai", event.getTanggalMulai());
        data.put("tanggalSelesai", event.getTanggalSelesai());
        data.put("hargaTiket", event.getHargaTiket());
        data.put("kapasitas", event.getKapasitas());
        data.put("statusAcara", event.getStatusAcara());
        data.put("lokasi", event.getLokasi());
        data.put("createdDate", event.getCreatedDate());
        data.put("modifiedDate", event.getModifiedDate());
        data.put("listImage", event.getListImage().stream().map(EventImage::getPath).collect(Collectors.toSet()));
        return data;
    }

}
