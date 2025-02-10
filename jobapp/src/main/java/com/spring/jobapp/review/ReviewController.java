package com.spring.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getCompanyReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getCompanyReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);

        if(review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
        boolean updated = reviewService.updateReviewById(companyId, reviewId, review);

        if(!updated){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addCompanyReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean created = reviewService.addCompanyReview(companyId, review);

        if(!created){
            return new ResponseEntity<>("Review not saved.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Review added successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean deleted = reviewService.deleteReviewById(companyId, reviewId);

        if(!deleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Review deleted successfully!", HttpStatus.CREATED);
    }
}
