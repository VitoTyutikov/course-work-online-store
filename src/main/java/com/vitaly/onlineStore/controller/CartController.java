package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.*;
import com.vitaly.onlineStore.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final OrdersService ordersService;
    private final OrderItemsService orderItemsService;
    private final CartService cartService;
    private final ProductsService productsService;
    private final ClientsService clientsService;

    @Autowired
    public CartController(OrdersService ordersService, OrderItemsService orderItemsService, CartService cartService,
            ProductsService productsService, ClientsService clientsService) {
        this.ordersService = ordersService;
        this.orderItemsService = orderItemsService;
        this.cartService = cartService;
        this.productsService = productsService;
        this.clientsService = clientsService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','CLIENT','MANAGER')")
    @GetMapping
    public String getUserCart(Authentication authentication, Model model) {

        Optional<ClientsEntity> client = clientsService.findByClientLogin(authentication.getName());
        List<CartEntity> cartItems = cartService.findByClientId(client.get().getClientId());
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','CLIENT','MANAGER')")
    @Transactional
    @RequestMapping(value = "/add/{productId}", method = { RequestMethod.GET, RequestMethod.POST })
    public String addToCart(Authentication authentication, @PathVariable Integer productId, Model model) {
        Optional<ProductsEntity> product = productsService.findByProductId(productId);
        Optional<ClientsEntity> user = clientsService.findByClientLogin(authentication.getName());
        Integer clientId = user.get().getClientId();
        if (product.isEmpty()) {
            model.addAttribute("message", "Product not found");
            return "error";
        }
        Optional<CartEntity> cartItem = cartService.findByClientIdAndProductId(clientId, productId);
        CartEntity cartEntity = new CartEntity();
        if (cartItem.isPresent()) {
            cartEntity = cartItem.get();
            cartEntity.setCountProducts(cartItem.get().getCountProducts() + 1);
        } else {
            // cartEntity = new CartEntity();
            cartEntity.setClientId(clientId);
            cartEntity.setProductId(product.get().getProductId());
            cartEntity.setCountProducts(1);
        }
        cartService.save(cartEntity);
        return "redirect:/cart";
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','CLIENT','MANAGER')")
    @Transactional
    @RequestMapping(value = "/remove/{productId}", method = { RequestMethod.GET, RequestMethod.DELETE })
    public String removeFromCart(Authentication authentication, @PathVariable Integer productId,
            @RequestParam(required = false) Integer quantity) {
        if (quantity == null) {
            quantity = 1;
        }
        Optional<ClientsEntity> user = clientsService.findByClientLogin(authentication.getName());
        Integer clientId = user.get().getClientId();

        Optional<CartEntity> cartItem = cartService.findByClientIdAndProductId(clientId, productId);
        CartEntity cartEntity;
        if (cartItem.isPresent()) {
            cartEntity = cartItem.get();
            cartEntity.setCountProducts(cartEntity.getCountProducts() - quantity);
            if (cartEntity.getCountProducts() < 1) {
                cartService.delete(cartItem.get());
            }
        } else {
            return "error";
        }
        return "redirect:/cart";
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','CLIENT','MANAGER')")
    @Transactional
    @RequestMapping(value = "/confirm", method = { RequestMethod.POST, RequestMethod.GET })
    public String confirmCart(Authentication authentication) {
        Optional<ClientsEntity> client = clientsService.findByClientLogin(authentication.getName());
        Integer clientId = client.get().getClientId();
        List<CartEntity> userCart = cartService.findByClientId(clientId);
        if (!userCart.isEmpty()) {
            OrdersEntity userOrder = new OrdersEntity();
            userOrder.setClientId(clientId);
            userOrder.setOrderDate(LocalDate.now());
            userOrder.setOrderStatus("Pending");
            ordersService.save(userOrder);

            Integer orderId = userOrder.getOrderId();
            for (CartEntity c : userCart) {
                OrderItemsEntity orderItem = new OrderItemsEntity();
                orderItem.setOrderId(orderId);
                orderItem.setProductId(c.getProductId());
                orderItem.setProductCount(c.getCountProducts());
                orderItem.setProductPrice(c.getProductByProductId().getProductPrice());
                orderItemsService.save(orderItem);
            }

            cartService.deleteAll(userCart);

            return "redirect:/orders.html";
        } else {
            return "redirect:/products.html";
        }
    }

}
