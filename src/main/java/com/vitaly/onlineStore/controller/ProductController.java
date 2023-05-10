package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ProductsEntity;
import com.vitaly.onlineStore.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Integer id, Model model) {
        Optional<ProductsEntity> product = productsService.getById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());

            return "product";
        } else {
            return "error";
        }

    }
}
