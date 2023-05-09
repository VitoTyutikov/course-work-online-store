package com.vitaly.onlineStore.service;

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

    public List<ProductsEntity> findAll() {
        return productsRepository.findAll();
    }

    public Optional<ProductsEntity> getById(Integer id) {
        return productsRepository.findById(id);//can use return productsRepository.findById(id).orElseThrow() instead ProductsEntity
    }

    public ProductsEntity save(ProductsEntity productsEntity) {
        productsRepository.save(productsEntity);
        return productsEntity;
    }

    public void deleteById(Integer id) {
        productsRepository.deleteById(id);
    }

    public List<ProductsEntity> findByProductNameStartsWithIgnoreCase(String productName) {
        return productsRepository.findByProductNameStartsWithIgnoreCase(productName);
    }


    public List<ProductsEntity> findByManufacturerId(Integer manufacturerId) {
        return productsRepository.findByManufacturerId(manufacturerId);
    }

    public List<ProductsEntity> findByCategoryId(Integer categoryId) {
        return productsRepository.findByCategoryId(categoryId);
    }

    public List<ProductsEntity> findByProductPriceGreaterThanEqual(Double priceFrom) {
        return productsRepository.findByProductPriceGreaterThanEqual(priceFrom);
    }

    public List<ProductsEntity> findByProductPriceLessThanEqual(Double priceTo) {
        return productsRepository.findByProductPriceLessThanEqual(priceTo);
    }

    public List<ProductsEntity> findByManufacturerName(String manufacturerName) {
        return productsRepository.findByManufacturerName(manufacturerName);
    }

    public List<ProductsEntity> findByCategoryName(String categoryName) {
        return productsRepository.findByCategoryName(categoryName);
    }
    public Optional<ProductsEntity> findByProductId(Integer id){
        return productsRepository.findById(id);
    }


}
