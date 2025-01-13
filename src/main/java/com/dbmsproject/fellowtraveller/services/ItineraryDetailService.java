package com.dbmsproject.fellowtraveller.services;

import com.dbmsproject.fellowtraveller.models.Itinerary;
import com.dbmsproject.fellowtraveller.models.ItineraryDetail;
import com.dbmsproject.fellowtraveller.repositories.ItineraryDetailRepository;
import com.dbmsproject.fellowtraveller.repositories.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItineraryDetailService {
    private final ItineraryDetailRepository itineraryDetailRepository;
    private final ItineraryRepository itineraryRepository;
    @Autowired
    public ItineraryDetailService(ItineraryDetailRepository itineraryDetailRepository, ItineraryService itineraryService, ItineraryRepository itineraryRepository) {
        this.itineraryDetailRepository = itineraryDetailRepository;
        this.itineraryRepository = itineraryRepository;
    }
    public ItineraryDetail createItineraryDetail(Long itineraryId, ItineraryDetail itineraryDetail) {
        Optional<Itinerary> existingItinerary = itineraryRepository.findById(itineraryId);
        if(existingItinerary.isPresent()) {
            return itineraryDetailRepository.save(itineraryDetail);
        }
        return null;
    }

    public ItineraryDetail updateItineraryDetail(Long detailId, ItineraryDetail itineraryDetail) {
        Optional<ItineraryDetail> existingItineraryDetail = itineraryDetailRepository.findById(detailId);
        if(existingItineraryDetail.isPresent()) {
            return itineraryDetailRepository.save(itineraryDetail);
        }
        return null;
    }

    public ItineraryDetail getItineraryDetailById(Long detailId) {
        Optional<ItineraryDetail> existingItineraryDetail = itineraryDetailRepository.findById(detailId);
        return existingItineraryDetail.orElse(null);
    }

    public void deleteItineraryDetail(Long detailId) {
        itineraryDetailRepository.deleteById(detailId);
    }
}
