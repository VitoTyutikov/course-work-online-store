package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
//    {

        List<ProductsEntity> byCategory = productsService.findByCategoryName(category);
        List<ProductsEntity> result = new ArrayList<>(byCategory);
//        result.addAll(byCategory);
//        // Filter by product name
        if (productName != null && !productName.isEmpty()) {
            List<ProductsEntity> productsByName = productsService.findByProductNameStartsWithIgnoreCase(productName);
            result.retainAll(productsByName);
        }
//
        // Filter by price range
//        List<ProductsEntity> productsByPrice = productsService.findAll();
        if (priceFrom != null) {
            result.retainAll(productsService.findByProductPriceGreaterThanEqual(priceFrom));
        }
        if (priceTo != null) {
            result.retainAll(productsService.findByProductPriceLessThanEqual(priceTo));
        }
////         Filter by manufacturer name
        if (manufacturersName != null && !manufacturersName.isEmpty()) {
            List<ProductsEntity> productsByManufacturer = new ArrayList<>();
            for (String manufacturer : manufacturersName) {
                productsByManufacturer.addAll(productsService.findByManufacturerName(manufacturer));
            }
            result.retainAll(productsByManufacturer);
        }

        return result;
    }


    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    public void deleteById(@PathVariable Integer id) {
        productsService.deleteById(id);
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST, RequestMethod.GET})
    public ProductsEntity newProduct(@RequestBody ProductsEntity newProductsEntity) {
        return productsService.save(newProductsEntity);
    }

}
