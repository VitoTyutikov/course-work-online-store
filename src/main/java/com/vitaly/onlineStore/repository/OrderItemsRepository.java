package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.OrderItemsEntity;
import com.vitaly.onlineStore.entity.OrderItemsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, OrderItemsPK> {

}
