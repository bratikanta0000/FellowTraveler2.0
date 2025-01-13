package com.dbmsproject.fellowtraveller.services;

import com.dbmsproject.fellowtraveller.models.Review;
import com.dbmsproject.fellowtraveller.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public Review updateReview(Long reviewId, Review review) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            return reviewRepository.save(review);
        }
        return null;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<Review> getReviewsByDestinationId(Long destinationId) {
        return reviewRepository.findByDestination_DestinationId(destinationId);
    }
}
