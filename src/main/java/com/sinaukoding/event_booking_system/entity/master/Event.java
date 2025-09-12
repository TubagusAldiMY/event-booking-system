package com.sinaukoding.event_booking_system.entity.master;

import com.sinaukoding.event_booking_system.entity.app.BaseEntity;
import com.sinaukoding.event_booking_system.model.enums.StatusAcara;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_event", indexes = {
        @Index(name = "idx_event_nama_acara", columnList = "namaAcara"),
        @Index(name = "idx_event_tanggal_mulai", columnList = "tanggalMulai"),
        @Index(name = "idx_event_status_acara", columnList = "statusAcara"),
        @Index(name = "idx_event_lokasi", columnList = "lokasi"),
        @Index(name = "idx_event_harga_tiket", columnList = "hargaTiket")
})
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String namaAcara;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String deskripsi;

    @Column(nullable = false)
    private LocalDateTime tanggalMulai;

    @Column(nullable = false)
    private LocalDateTime tanggalSelesai;

    @Min(value = 0, message = "Harga Tiket tidak boleh negatif")
    @Column(nullable = false)
    private Double hargaTiket;

    @Min(value = 0, message = "Kapasitas tidak boleh negatif")
    @Column(nullable = false)
    private Integer kapasitas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAcara statusAcara;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String lokasi;

//    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<EventImage> listImage = new HashSet<>();

}
