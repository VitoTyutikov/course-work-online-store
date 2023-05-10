package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.CategoriesEntity;
import com.vitaly.onlineStore.service.CategoriesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void deleteById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        categoriesService.deleteById(id);
        response.sendRedirect("http://localhost:8080/categories");
    }
}
