package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ProductsEntity> findAll() {
        return productsRepository.findAll();
    }

    public ProductsEntity getById(Integer id) {
        return productsRepository.findById(id).orElseThrow();//can use Optional<ProductsEntity> instead ProductsEntity
    }

    public ProductsEntity save(ProductsEntity productsEntity) {
        productsRepository.save(productsEntity);
        return productsEntity;
    }

    public void deleteById(Integer id) {
        productsRepository.deleteById(id);
    }

    
}
