package com.sinaukoding.event_booking_system.model.filter;

import com.sinaukoding.event_booking_system.model.enums.StatusAcara;
import java.time.LocalDate;

public record EventFilterRecord(
        String namaAcara,
        String lokasi,
        LocalDate tanggalMulai,
        LocalDate tanggalSelesai,
        Double hargaTiketBawah,
        Double hargaTiketAtas,
        StatusAcara statusAcara
) {}
