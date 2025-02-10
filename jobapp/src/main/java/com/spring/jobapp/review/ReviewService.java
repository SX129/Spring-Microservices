package com.spring.jobapp.review;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ReviewService {
    List<Review> getCompanyReviews(Long companyId);
    Review getReviewById(Long companyId, Long reviewId);
    boolean updateReviewById(Long companyId, Long reviewId, Review review);
    boolean deleteReviewById(Long companyId, Long reviewId);
    boolean addCompanyReview(Long companyId, Review review);
}
