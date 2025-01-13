package com.dbmsproject.fellowtraveller.controllers;

import com.dbmsproject.fellowtraveller.models.Destination;
import com.dbmsproject.fellowtraveller.models.Review;
import com.dbmsproject.fellowtraveller.services.DestinationService;
import com.dbmsproject.fellowtraveller.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    private final DestinationService destinationService;
    private final ReviewService reviewService;
    @Autowired
    public DestinationController(DestinationService destinationService, ReviewService reviewService) {
        this.destinationService = destinationService;
        this.reviewService = reviewService;
    }
    @GetMapping
    public List<Destination> getAllDestinations() { return destinationService.getAllDestinations(); }
    @GetMapping("/search")
    public List<Destination> searchDestinations(@RequestParam String query) {
        return destinationService.searchDestinations(query);
    }
    @GetMapping("/{destinationId}")
    public Destination getDestinationById(@PathVariable Long destinationId) {
        return destinationService.getDestinationById(destinationId);
    }
    @GetMapping("/{destinationId}/reviews")
    public List<Review> getReviewsByDestinationId(@PathVariable Long destinationId) {
        return reviewService.getReviewsByDestinationId(destinationId);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Destination> createDestination(
            @RequestPart("destination") String destinationJson,
            @RequestPart("coverPhoto") MultipartFile coverPhoto) throws IOException {

        // Parse JSON string into Destination object
        ObjectMapper objectMapper = new ObjectMapper();
        Destination destination = objectMapper.readValue(destinationJson, Destination.class);

        // Convert coverPhoto to byte[] and set it in the destination
        if (!coverPhoto.isEmpty()) {
            destination.setCoverPhoto(coverPhoto.getBytes());
        }

        // Save destination
        Destination createdDestination = destinationService.createDestination(destination);

        // Return the created destination with location header
        URI location = URI.create("/api/destinations/" + createdDestination.getDestinationId());
        return ResponseEntity.created(location).body(createdDestination);
    }
}
