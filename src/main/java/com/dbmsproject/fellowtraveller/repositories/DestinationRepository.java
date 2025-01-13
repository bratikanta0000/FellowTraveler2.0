package com.dbmsproject.fellowtraveller.repositories;

import com.dbmsproject.fellowtraveller.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query("SELECT d FROM Destination d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Destination> searchDestinations(String query);
}
