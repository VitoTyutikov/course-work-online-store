package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import com.vitaly.onlineStore.service.CategoriesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/all")
    public List<CategoriesEntity> findAll() {
        return categoriesService.findAll();
    }

    @GetMapping("/{id}")
    public CategoriesEntity findById(@PathVariable Integer id) {
        return categoriesService.getById(id);
    }
}
