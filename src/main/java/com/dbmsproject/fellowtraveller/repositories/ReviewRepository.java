package com.dbmsproject.fellowtraveller.repositories;

import com.dbmsproject.fellowtraveller.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDestination_DestinationId(Long destinationId);
}
