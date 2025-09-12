package com.sinaukoding.event_booking_system.entity.managementuser;

import com.sinaukoding.event_booking_system.entity.app.BaseEntity;
//import com.sinaukoding.event_booking_system.entity.master.Booking;
import com.sinaukoding.event_booking_system.model.enums.Role;
import com.sinaukoding.event_booking_system.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_user", indexes = {
        @Index(name = "idx_user_created_date", columnList = "createdDate"),
        @Index(name = "idx_user_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_email", columnList = "email"),
        @Index(name = "idx_user_status", columnList = "status"),
        @Index(name = "idx_user_role", columnList = "role")
})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String nama;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String token;
    private LocalDateTime expiredTokenAt;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<Booking> bookings = new HashSet<>();

}
