package com.microservice.reviewms.review.messaging;

import com.microservice.reviewms.review.Review;
import com.microservice.reviewms.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setComapnyId(review.getCompanyId());

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}
