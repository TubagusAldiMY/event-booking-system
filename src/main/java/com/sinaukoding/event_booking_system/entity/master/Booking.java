package com.sinaukoding.event_booking_system.entity.master;


import com.sinaukoding.event_booking_system.entity.app.BaseEntity;
import com.sinaukoding.event_booking_system.entity.managementuser.User;
import com.sinaukoding.event_booking_system.model.enums.StatusPembayaran;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_booking", indexes = {
        @Index(name = "idx_booking_id_user", columnList = "id_user"),
        @Index(name = "idx_booking_id_event", columnList = "id_event"),
        @Index(name = "idx_booking_status_pembayaran", columnList = "statusPembayaran"),
        @Index(name = "idx_booking_tanggal_booking", columnList = "tanggalBooking")

})
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private LocalDateTime tanggalBooking;

    @Min(value = 1, message = "Jumlah Tiket minimal 1")
    @Column(nullable = false)
    private Integer jumlahTiket;

    @Min(value = 0, message = "Harga Tiket tidak boleh negatif")
    @Column(nullable = false)
    private Double totalHarga;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPembayaran statusPembayaran;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

}
