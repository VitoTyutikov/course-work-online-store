package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import com.vitaly.onlineStore.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;
    public CategoriesService(CategoriesRepository categoriesRepository){
        this.categoriesRepository=categoriesRepository;
    }
    public List<CategoriesEntity> getAll(){
        return  categoriesRepository.findAll();
    }
    public Optional<CategoriesEntity> getById(Integer id){
        return categoriesRepository.findById(id);
    }

    public Integer save(CategoriesEntity category){
        categoriesRepository.save(category);
        return category.getCategoryId();
    }
    public void deleteById(Integer id){
        categoriesRepository.deleteById(id);
    }

}
