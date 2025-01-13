package com.dbmsproject.fellowtraveller.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "itinerary_id", nullable = false)
    private Itinerary itinerary;

    private Long destinationId;

    private Long userid;

    private String serviceType;

    private LocalDate bookingDate;

    private BigDecimal cost;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();
}
