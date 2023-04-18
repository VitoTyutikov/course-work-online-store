package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import com.vitaly.onlineStore.service.CategoriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<CategoriesEntity> findAll() {
        return categoriesService.findAll();
    }

    @GetMapping("/{id}")
    public CategoriesEntity findById(@PathVariable Integer id) {
        return categoriesService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        categoriesService.deleteById(id);
    }
    @DeleteMapping("/delete/{name}")
    public void deleteByCategoryName(@PathVariable String name){
        categoriesService.deleteByCategoryName(name);
    }
}
