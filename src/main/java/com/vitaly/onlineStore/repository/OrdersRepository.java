package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
    List<OrdersEntity> findByClientId(Integer clientId);
}
