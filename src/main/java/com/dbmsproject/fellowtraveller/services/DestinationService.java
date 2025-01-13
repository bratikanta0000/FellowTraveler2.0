package com.dbmsproject.fellowtraveller.services;

import com.dbmsproject.fellowtraveller.models.Destination;
import com.dbmsproject.fellowtraveller.models.Review;
import com.dbmsproject.fellowtraveller.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> searchDestinations(String query) {
        return destinationRepository.searchDestinations(query);
    }

    public Destination getDestinationById(Long destinationId) {
        return destinationRepository.findById(destinationId).orElse(null);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }
    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);

    }
}
