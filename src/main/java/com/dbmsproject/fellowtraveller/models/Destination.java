package com.dbmsproject.fellowtraveller.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    @Column(nullable = false, length = 200)
    private String name;

    private String description;

    @Column(nullable = false, length = 1024)
    private String location;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    @Column(nullable = true)
    private Double rating;

    @Lob
    @Column(nullable = true, columnDefinition = "LONGBLOB" ) // Allow this to be optional
    private byte[] coverPhoto;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = true)
    private List<Review> reviews;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
