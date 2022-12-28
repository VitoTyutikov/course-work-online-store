package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.DeliveriesEntity;
import com.vitaly.onlineStore.entity.DeliveriesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveriesRepository extends JpaRepository<DeliveriesEntity, DeliveriesPK> {
}
