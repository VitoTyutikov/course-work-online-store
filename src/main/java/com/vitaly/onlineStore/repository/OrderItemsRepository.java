package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.OrderItemsEntity;
import com.vitaly.onlineStore.entity.OrderItemsPK;
import com.vitaly.onlineStore.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, OrderItemsPK> {
    List<OrderItemsEntity> findByOrderId(Integer orderId);
}
