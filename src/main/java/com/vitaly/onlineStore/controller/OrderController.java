package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.OrderItemsEntity;
import com.vitaly.onlineStore.service.OrderItemsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    private final OrderItemsService orderItemsService;

    public OrderController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("/{orderId}")
    public String findByOrderId(@PathVariable Integer orderId, Model model){
        List<OrderItemsEntity> orderItems = orderItemsService.findByOrderId(orderId);
        model.addAttribute("orderItems", orderItems);
        return "order";
    }
}
