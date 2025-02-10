package com.microservice.reviewms.review;

import com.microservice.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getCompanyReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getCompanyReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        Review review = reviewService.getReviewById( reviewId);

        if(review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId, @RequestBody Review review){
        boolean updated = reviewService.updateReviewById(reviewId, review);

        if(!updated){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addCompanyReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean created = reviewService.addCompanyReview(companyId, review);

        if(!created){
            return new ResponseEntity<>("Review not saved.", HttpStatus.NOT_FOUND);
        }

        reviewMessageProducer.sendMessage(review);

        return new ResponseEntity<>("Review added successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){
        boolean deleted = reviewService.deleteReviewById(reviewId);

        if(!deleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Review deleted successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getCompanyReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
