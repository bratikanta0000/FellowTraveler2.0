package com.dbmsproject.fellowtraveller.controllers;

import com.dbmsproject.fellowtraveller.models.Booking;
import com.dbmsproject.fellowtraveller.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        URI location = URI.create("/api/bookings/" + createdBooking.getBookingId());
        return ResponseEntity.created(location).body(createdBooking);
    }
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @GetMapping("/foritinerary/{itineraryId}")
    public List<Booking> getBookingsByItineraryId(Long itineraryId) {
        return bookingService.getBookingsByItineraryId(itineraryId);
    }
    @GetMapping("/foruser/{userId}")
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
