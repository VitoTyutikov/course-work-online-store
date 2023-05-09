package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.dto.ReviewsDTO;
import com.vitaly.onlineStore.entity.ReviewsEntity;
import com.vitaly.onlineStore.service.ClientsService;
import com.vitaly.onlineStore.service.ReviewsService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;
    private final ClientsService clientsService;

    public ReviewsController(ReviewsService reviewsService, ClientsService clientsService) {
        this.reviewsService = reviewsService;
        this.clientsService = clientsService;
    }

    @GetMapping("{productId}")
    public String findByProductId(@PathVariable Integer productId, Model model) {
        List<ReviewsEntity> reviews = reviewsService.findByProductId(productId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", new ReviewsDTO());
        return "reviews";
    }

    @RequestMapping(value = "/add/{productId}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public String addReview(@PathVariable Integer productId, @ModelAttribute ReviewsDTO review) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (!name.equals("anonymousUser")) {

            ReviewsEntity reviewEntity = new ReviewsEntity();
            reviewEntity.setReviewDate(LocalDate.now());
            reviewEntity.setReviewRating(review.getReviewRating());
            reviewEntity.setReviewText(review.getReviewText());
            reviewEntity.setReviewTitle(review.getReviewTitle());
            reviewEntity.setClientId(clientsService.findByClientLogin(name).get().getClientId());
            reviewEntity.setProductId(productId);
            reviewsService.save(reviewEntity);
            return "redirect:/reviews/" + productId;
//            return "reviews";
        } else {
            return "redirect:/login";
//            return "reviews";
        }
    }
}
