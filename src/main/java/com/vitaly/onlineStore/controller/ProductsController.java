package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }
//    @CrossOrigin
    @GetMapping("/all")
    public List<ProductsEntity> getAll() {
//        return productsService.getAll() == null ? productsService.getAll() : null;
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public ProductsEntity getById(@PathVariable Integer id) {
        return productsService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        productsService.deleteById(id);
    }

    @PostMapping("/add")
    public ProductsEntity newProduct(@RequestBody ProductsEntity newProductsEntity) {
        return productsService.save(newProductsEntity);
    }

}
