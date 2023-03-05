package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    //    @RequestParam(name = "discount", required = false) Integer discount
    //
    @GetMapping({"", "/"})
    public List<ProductsEntity> getProducts(
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "priceFrom", required = false, defaultValue = "0") Double priceFrom,
            @RequestParam(name = "priceTo", required = false) Double priceTo,
            @RequestParam(name = "categoryName", required = false) List<String> categoryName,
            @RequestParam(name = "manufacturersName", required = false) List<String> manufacturersName) {
        List<ProductsEntity> result = new ArrayList<>();

        // Filter by product name
        if (productName != null) {
            List<ProductsEntity> productsByName = productsService.findByProductNameStartsWithIgnoreCase(productName);
            result.addAll(productsByName);
        }

        // Filter by price range
        List<ProductsEntity> productsByPrice = productsService.findAll();
        if (priceFrom != null) {
            productsByPrice.retainAll(productsService.findByProductPriceGreaterThanEqual(priceFrom));
        }
        if (priceTo != null) {
            productsByPrice.retainAll(productsService.findByProductPriceLessThanEqual(priceTo));
        }
        result.addAll(productsByPrice);

        // Filter by category name
        if (categoryName != null && !categoryName.isEmpty()) {
            List<ProductsEntity> productsByCategory = new ArrayList<>();
            for (String category : categoryName) {
                productsByCategory.addAll(productsService.findByCategoryName(category));
            }
            result.retainAll(productsByCategory);
        }

        // Filter by manufacturer name
        if (manufacturersName != null && !manufacturersName.isEmpty()) {
            List<ProductsEntity> productsByManufacturer = new ArrayList<>();
            for (String manufacturer : manufacturersName) {
                productsByManufacturer.addAll(productsService.findByManufacturerName(manufacturer));
            }
            result.retainAll(productsByManufacturer);
        }

        return result;

        /*List<ProductsEntity> result = new ArrayList<>();
        System.out.println("Product name = " + productName);
        if (productName != null) {
            result.addAll(productsService.findByProductNameStartsWithIgnoreCase(productName));
        }
        if (priceFrom != null) {
            result.removeAll(productsService.findByProductPriceLessThanEqual(priceFrom));
        }
        if (priceTo != null) {
            result.removeAll(productsService.findByProductPriceGreaterThanEqual(priceTo));
        }
        if (categoryName != null) {
            for(String s : categoryName){
                result.addAll(productsService.findByManufacturerName(s));
            }
        }
        if (manufacturersName != null) {
            for(String s : manufacturersName){
                result.addAll(productsService.findByManufacturerName(s));
            }
        }
        return result;*/
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
