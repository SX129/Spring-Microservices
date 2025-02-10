package com.microservice.reviewms.review.impl;

import com.microservice.reviewms.review.Review;
import com.microservice.reviewms.review.ReviewRepository;
import com.microservice.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<Review> getCompanyReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReviewById(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());

            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review != null){
            reviewRepository.delete(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean addCompanyReview(Long companyId, Review review) {
        if(companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);

            return true;
        }
        return false;
    }
}
