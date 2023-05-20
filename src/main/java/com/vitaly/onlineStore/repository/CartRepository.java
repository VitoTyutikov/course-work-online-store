package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findByClientId(Integer userId);

    Optional<CartEntity> findByClientIdAndProductId(Integer clientId, Integer productId);

    void deleteByClientIdAndProductId(Integer userId, Integer productId);
}
