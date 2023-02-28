package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.DeliveriesEntity;
import com.vitaly.onlineStore.entity.DeliveriesPK;
import com.vitaly.onlineStore.repository.DeliveriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveriesService {
    private final DeliveriesRepository deliveriesRepository;

    public DeliveriesService(DeliveriesRepository deliveriesRepository) {
        this.deliveriesRepository = deliveriesRepository;
    }

    public List<DeliveriesEntity> findAll() {
        return deliveriesRepository.findAll();
    }

    public DeliveriesEntity findById(DeliveriesPK id) {
        return deliveriesRepository.findById(id).orElseThrow();
    }

    public DeliveriesPK save(DeliveriesEntity deliveriesEntity) {//it's right?
        deliveriesRepository.save(deliveriesEntity);
        return deliveriesEntity.getId();
    }

    public void deleteById(DeliveriesPK id) {
        deliveriesRepository.deleteById(id);
    }


}
