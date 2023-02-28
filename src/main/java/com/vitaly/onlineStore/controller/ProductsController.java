package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/all")
    public List<ProductsEntity> getAll() {
//        return productsService.getAll() == null ? productsService.getAll() : null;
        return productsService.getAll();
    }

    @GetMapping("/{id}")
    public ProductsEntity getById(@PathVariable Integer id){
        return productsService.getById(id);
    }
}
