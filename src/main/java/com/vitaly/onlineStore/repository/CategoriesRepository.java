package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
    void deleteByCategoryName(String categoryName);
}
