package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ReviewsEntity;
import com.vitaly.onlineStore.service.ReviewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("{productId}")
    public String findByProductId(@PathVariable Integer productId, Model model) {
        model.addAttribute("reviews", reviewsService.findByProductId(productId));
        return "reviews";
    }
}
