package com.o4.mobility.dao.entities.booking;

import com.o4.mobility.common.dtos.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "om_bookings")
@EntityListeners(AuditingEntityListener.class)
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long customerId;
    private Long driverId;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private String pickupLat;
    private String pickupLong;
    @Column(name = "drop_off_lat")
    private String dropOffLat;
    @Column(name = "drop_off_long")
    private String dropOffLong;

    @CreatedDate
    private Date dtCreated;
    @LastModifiedDate
    private Date dtUpdated;
}
