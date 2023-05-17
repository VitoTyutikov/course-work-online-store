package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.ReviewsEntity;
import com.vitaly.onlineStore.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;
    private final ProductsService productsService;

    @Autowired
    public ReviewsService(ReviewsRepository reviewsRepository, ProductsService productsService) {
        this.reviewsRepository = reviewsRepository;
        this.productsService = productsService;
    }

    public List<ReviewsEntity> findByProductId(Integer productId) {
        return reviewsRepository.findByProductId(productId);
    }

    public ReviewsEntity save(ReviewsEntity review) {
        reviewsRepository.save(review);
        updateProductRating(review.getProductId());
        return review;
    }


    public void updateProductRating(Integer productId) {
        Double sum = 0.0;
        Double rating;
        List<ReviewsEntity> reviews = reviewsRepository.findByProductId(productId);
        if (!reviews.isEmpty()) {
            for (ReviewsEntity review : reviews) {
                sum += review.getReviewRating();
            }
            rating = sum / reviews.size();
        } else {
            rating = 0.0;
        }
        rating = (double) Math.round(rating * 100) / 100;

        productsService.updateRating(productId, rating);
    }
}
