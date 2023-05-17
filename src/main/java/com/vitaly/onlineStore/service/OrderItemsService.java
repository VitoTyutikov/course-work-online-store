package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.OrderItemsEntity;
import com.vitaly.onlineStore.entity.OrderItemsPK;
import com.vitaly.onlineStore.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;


    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<OrderItemsEntity> findAll() {
        return orderItemsRepository.findAll();
    }

    public List<OrderItemsEntity> findByOrderId(Integer orderId){
        return orderItemsRepository.findByOrderId(orderId);
    }

    public OrderItemsEntity findById(OrderItemsPK id) {
        return orderItemsRepository.findById(id).orElseThrow();
    }

    public OrderItemsPK save(OrderItemsEntity orderItemsEntity) {
        orderItemsRepository.save(orderItemsEntity);
        return orderItemsEntity.getId();
    }

    public void deleteById(OrderItemsPK id) {
        orderItemsRepository.deleteById(id);
    }
}
