package com.dbmsproject.fellowtraveller.repositories;

import com.dbmsproject.fellowtraveller.models.ItineraryDetail;
import com.dbmsproject.fellowtraveller.services.ItineraryDetailService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryDetailRepository extends JpaRepository<ItineraryDetail, Long> {
}
