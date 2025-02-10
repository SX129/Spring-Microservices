package com.spring.jobapp.review.impl;

import com.spring.jobapp.company.Company;
import com.spring.jobapp.company.CompanyService;
import com.spring.jobapp.review.Review;
import com.spring.jobapp.review.ReviewRepository;
import com.spring.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getCompanyReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = getCompanyReviews(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        Review review = getReviewById(companyId, reviewId);

        if(review != null){
            review.setDescription(updatedReview.getDescription());
            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());

            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        Review review = getReviewById(companyId, reviewId);
        if(review != null){
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);

            reviewRepository.deleteById(reviewId);
            return true;
        }

        return false;
    }

    @Override
    public boolean addCompanyReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }
}
