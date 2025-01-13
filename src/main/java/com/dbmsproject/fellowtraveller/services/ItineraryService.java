package com.dbmsproject.fellowtraveller.services;

import com.dbmsproject.fellowtraveller.models.Itinerary;
import com.dbmsproject.fellowtraveller.repositories.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {
    public final ItineraryRepository itineraryRepository;

    public ItineraryService(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public Itinerary getItineraryById(Long itineraryId) {
        return itineraryRepository.findById(itineraryId).orElse(null);
    }

    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public Itinerary updateItinerary(Long itineraryId, Itinerary itinerary) {
        Optional<Itinerary> existingItinerary = itineraryRepository.findById(itineraryId);
        if (existingItinerary.isPresent()) {
            return itineraryRepository.save(itinerary);
        }
        return null;
    }

    public void deleteItinerary(Long itineraryId) {
        itineraryRepository.deleteById(itineraryId);
    }
}
