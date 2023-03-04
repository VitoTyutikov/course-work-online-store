package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import com.vitaly.onlineStore.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<CategoriesEntity> findAll() {
        return categoriesRepository.findAll();
    }

    public CategoriesEntity getById(Integer id) {
        return categoriesRepository.findById(id).orElseThrow();
    }

    public Integer save(CategoriesEntity category) {
        categoriesRepository.save(category);
        return category.getCategoryId();
    }

    public void deleteById(Integer id) {
        categoriesRepository.deleteById(id);
    }


}
