package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }
    public List<ProductsEntity> getAll(){
        return  productsRepository.findAll();
    }
    public Optional<ProductsEntity> getById(Integer id){
        return productsRepository.findById(id);
    }

    public Integer save(ProductsEntity category){
        productsRepository.save(category);
        return category.getCategoryId();
    }
    public void deleteById(Integer id){
        productsRepository.deleteById(id);
    }
}
