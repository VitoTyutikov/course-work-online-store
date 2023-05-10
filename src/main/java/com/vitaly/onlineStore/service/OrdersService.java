package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.OrdersEntity;
import com.vitaly.onlineStore.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrdersEntity> findAll() {
        return ordersRepository.findAll();
    }

    public OrdersEntity findById(Integer id) {
        return ordersRepository.findById(id).orElseThrow();
    }

    public Integer save(OrdersEntity ordersEntity) {
        ordersRepository.save(ordersEntity);
        return ordersEntity.getOrderId();
    }

    public void deleteById(Integer id) {
        ordersRepository.deleteById(id);
    }

    public List<OrdersEntity> findByClientId(Integer clientId){
        return ordersRepository.findByClientId(clientId);
    }
    public Optional<OrdersEntity> findOrder(Integer orderId){
        return ordersRepository.findById(orderId);
    }
}
