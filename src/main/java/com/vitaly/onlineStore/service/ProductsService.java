package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.repository.ProductsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<ProductsEntity> findByFilter(String category, String productName, Double priceFrom, Double priceTo, List<String> manufacturersName) {
        List<ProductsEntity> byCategory = this.findByCategoryName(category);
        List<ProductsEntity> result = new ArrayList<>(byCategory);

        //Filter by product name
        if (productName != null && !productName.isEmpty()) {
            List<ProductsEntity> productsByName = this.findByProductNameStartsWithIgnoreCase(productName);
            result.retainAll(productsByName);
        }
        // Filter by price range
        if (priceFrom != null) {
            result.retainAll(this.findByProductPriceGreaterThanEqual(priceFrom));
        }
        if (priceTo != null) {
            result.retainAll(this.findByProductPriceLessThanEqual(priceTo));
        }
        //Filter by manufacturer name
        if (manufacturersName != null && !manufacturersName.isEmpty()) {
            List<ProductsEntity> productsByManufacturer = new ArrayList<>();
            for (String manufacturer : manufacturersName) {
                productsByManufacturer.addAll(this.findByManufacturerName(manufacturer));
            }
            result.retainAll(productsByManufacturer);
        }
        return result;
    }

    public Optional<ProductsEntity> getById(Integer id) {
        return productsRepository.findById(id);
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

    public Optional<ProductsEntity> findByProductId(Integer id) {
        return productsRepository.findById(id);
    }

    @Transactional
    public void updateRating(Integer productId, Double rating) {
        productsRepository.updateProductRating(productId, rating);
    }
}
