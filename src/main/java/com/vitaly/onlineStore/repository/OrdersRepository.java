package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {

}
