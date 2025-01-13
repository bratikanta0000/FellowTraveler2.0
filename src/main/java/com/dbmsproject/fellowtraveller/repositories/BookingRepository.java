package com.dbmsproject.fellowtraveller.repositories;

import com.dbmsproject.fellowtraveller.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByItinerary_ItineraryId(Long itineraryId);

    List<Booking> findAllByUserid(Long userId);
}
