package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.service.ProductsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController implements Serializable {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<ProductsEntity> findAll() {
        return productsService.findAll();
    }

    @GetMapping("/{category}")
    public List<ProductsEntity> findByFilter(
            @PathVariable String category,
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "priceFrom", required = false) Double priceFrom,
            @RequestParam(name = "priceTo", required = false) Double priceTo,
            @RequestParam(name = "manufacturersName", required = false) List<String> manufacturersName) {
        return productsService.findByFilter(category, productName, priceFrom, priceTo, manufacturersName);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    public void deleteById(@PathVariable Integer id) {
        productsService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    public ProductsEntity newProduct(@RequestBody ProductsEntity newProductsEntity) {
        return productsService.save(newProductsEntity);
    }

    @GetMapping("/id={id}")
    public ProductsEntity getById(@PathVariable Integer id) {
        Optional<ProductsEntity> product = productsService.getById(id);
        if (product.isPresent())
            return productsService.getById(id).get();
        else {
            return null;
        }
    }
}
