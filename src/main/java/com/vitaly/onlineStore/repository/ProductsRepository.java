package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer> {


    List<ProductsEntity> findByProductNameStartsWithIgnoreCase(String productName);

    List<ProductsEntity> findByProductPriceBetween(Double priceFrom, Double priceTo);

    List<ProductsEntity> findByProductPriceGreaterThanEqual(Double priceFrom);

    List<ProductsEntity> findByProductPriceLessThanEqual(Double priceTo);

    List<ProductsEntity> findByManufacturerId(Integer manufacturerId);

    List<ProductsEntity> findByCategoryId(Integer categoryId);

    @Query("SELECT p FROM ProductsEntity p JOIN p.manufacturersByManufacturerId m WHERE UPPER(m.manufacturerName) = UPPER(:manufacturerName)")
    List<ProductsEntity> findByManufacturerName(@Param("manufacturerName") String manufacturerName);

    @Query("SELECT p FROM ProductsEntity p JOIN p.categoriesByCategoryId c WHERE UPPER(c.categoryName) = UPPER(:categoryName)")
    List<ProductsEntity> findByCategoryName(@Param("categoryName") String categoryName);

    @Modifying
    @Query("update ProductsEntity p set p.productRating = :rating where p.productId = :id")
    void updateProductRating(@Param("id") Integer id, @Param("rating") Double rating);


}
