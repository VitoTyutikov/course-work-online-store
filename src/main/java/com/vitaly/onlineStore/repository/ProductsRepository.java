package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer> {

    List<ProductsEntity> findByProductNameLike(String productName);

    List<ProductsEntity> findByProductPriceBetween(Double price1, Double price2);

    List<ProductsEntity> findByManufacturerId(Integer manufacturerId);
}
