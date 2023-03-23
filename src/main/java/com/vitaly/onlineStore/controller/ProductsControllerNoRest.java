package com.vitaly.onlineStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("products")
public class ProductsControllerNoRest {
    @GetMapping
    public String getProducts(){
        return "products.html";
    }
}
