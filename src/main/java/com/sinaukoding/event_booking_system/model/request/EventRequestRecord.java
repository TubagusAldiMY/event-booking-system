package com.sinaukoding.event_booking_system.model.request;

import com.sinaukoding.event_booking_system.model.enums.StatusAcara;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record EventRequestRecord(String id,
                                 @NotBlank(message = "Nama Acara tidak boleh kosong")String namaAcara,
                                 @NotBlank(message = "Deskripsi tidak boleh kosong")String deskripsi,
                                 @NotNull(message = "Tanggal Mulai boleh kosong")LocalDateTime tanggalMulai,
                                 @NotNull(message = "Tanggal Selesai tidak boleh kosong")LocalDateTime tanggalSelesai,
                                 @NotNull(message = "Harga tiket tidak boleh kosong")Double hargaTiket,
                                 @NotNull(message = "Kapasitas tidak boleh kosong")Integer kapasitas,
                                 @NotNull(message = "Status tidak boleh kosong") StatusAcara statusAcara,
                                 @NotBlank(message = "Lokasi tidak boleh kosong")String lokasi,
                                 @NotEmpty(message = "Gambar tidak boleh kosong") Set<String> listImage) {
}
