package com.dbmsproject.fellowtraveller.controllers;

import com.dbmsproject.fellowtraveller.models.Itinerary;
import com.dbmsproject.fellowtraveller.models.ItineraryDetail;
import com.dbmsproject.fellowtraveller.services.ItineraryDetailService;
import com.dbmsproject.fellowtraveller.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {
    private final ItineraryService itineraryService;
    private final ItineraryDetailService itineraryDetailService;
    @Autowired
    public ItineraryController(ItineraryService itineraryService, ItineraryDetailService itineraryDetailService) {
        this.itineraryService = itineraryService;
        this.itineraryDetailService = itineraryDetailService;
    }
    @PostMapping
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        Itinerary createdItinerary = itineraryService.createItinerary(itinerary);
        URI uri = URI.create("/api/itineraries/" + createdItinerary.getItineraryId());
        return ResponseEntity.created(uri).body(createdItinerary);
    }
//    @GetMapping("/foruser/{userId}")
//    public ResponseEntity<List<Itinerary>> getItineraryByUserId(@PathVariable Long userId) {
//        return ResponseEntity.ok(itineraryService.getItineraryByUserId(userId));
//    }
    @GetMapping("/{itineraryId}")
    public ResponseEntity<Itinerary> getItineraryById(@PathVariable Long itineraryId) {
        return ResponseEntity.ok(itineraryService.getItineraryById(itineraryId));
    }
    @GetMapping
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        return ResponseEntity.ok(itineraryService.getAllItineraries());
    }
    @PutMapping("/{itineraryId}")
    public ResponseEntity<Itinerary> updateItinerary(@PathVariable Long itineraryId, @RequestBody Itinerary itinerary) {
        Itinerary existingItinerary = itineraryService.getItineraryById(itineraryId);
        if (existingItinerary == null) {
            return ResponseEntity.notFound().build();
        }
        Itinerary updatedItinerary = itineraryService.updateItinerary(itineraryId, itinerary);
        return ResponseEntity.ok(updatedItinerary);
    }
    @DeleteMapping("/{itineraryId}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable Long itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{itineraryId}/details")
    public ResponseEntity<ItineraryDetail> createItineraryDetail(@PathVariable Long itineraryId, @RequestBody ItineraryDetail itineraryDetail) {
        ItineraryDetail createdItineraryDetail = itineraryDetailService.createItineraryDetail(itineraryId, itineraryDetail);
        if(createdItineraryDetail == null) {
            return ResponseEntity.notFound().build();
        }
        URI uri = URI.create("/api/itineraries/" + itineraryId + "/details/" + createdItineraryDetail.getDetailId());
        return ResponseEntity.created(uri).body(createdItineraryDetail);
    }
    @PutMapping("/details/{detailId}")
    public ResponseEntity<ItineraryDetail> updateItineraryDetail(@PathVariable Long detailId, @RequestBody ItineraryDetail itineraryDetail) {
        ItineraryDetail existingItineraryDetail = itineraryDetailService.getItineraryDetailById(detailId);
        if (existingItineraryDetail == null) {
            return ResponseEntity.notFound().build();
        }
        ItineraryDetail updatedItineraryDetail = itineraryDetailService.updateItineraryDetail(detailId, itineraryDetail);
        return ResponseEntity.ok(updatedItineraryDetail);
    }
    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<Void> deleteItineraryDetail(@PathVariable Long detailId) {
        itineraryDetailService.deleteItineraryDetail(detailId);
        return ResponseEntity.noContent().build();
    }
}
