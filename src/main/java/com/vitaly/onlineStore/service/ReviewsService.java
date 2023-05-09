package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.ReviewsEntity;
import com.vitaly.onlineStore.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public List<ReviewsEntity> findByProductId(Integer productId) {
        return reviewsRepository.findByProductId(productId);
    }
    public ReviewsEntity save(ReviewsEntity review){
        return reviewsRepository.save(review);
    }
}
