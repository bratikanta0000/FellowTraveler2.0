package com.dbmsproject.fellowtraveller.services;

import com.dbmsproject.fellowtraveller.models.Booking;
import com.dbmsproject.fellowtraveller.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    public final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByItineraryId(Long itineraryId) {
        return bookingRepository.findByItinerary_ItineraryId(itineraryId);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findAllByUserid(userId);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
