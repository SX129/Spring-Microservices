package com.microservice.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getCompanyReviews(Long companyId);
    Review getReviewById(Long reviewId);
    boolean updateReviewById(Long reviewId, Review review);
    boolean deleteReviewById(Long reviewId);
    boolean addCompanyReview(Long companyId, Review review);
}
