package com.dbmsproject.fellowtraveller.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Itineraries")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itineraryId;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal totalBudget;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;
}
