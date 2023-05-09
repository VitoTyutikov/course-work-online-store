package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {
    List<ReviewsEntity> findByProductId(Integer productId);
}
