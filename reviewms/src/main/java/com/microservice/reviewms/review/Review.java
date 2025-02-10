package com.microservice.reviewms.review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long Id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;

    public Review() {
    }

    public Review(Long id, String title, String description, double rating) {
        Id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
