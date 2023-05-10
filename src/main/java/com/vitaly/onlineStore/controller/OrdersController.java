package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.entity.OrdersEntity;
import com.vitaly.onlineStore.service.ClientsService;
import com.vitaly.onlineStore.service.OrdersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrdersController {
    private final ClientsService clientsService;
    private final OrdersService ordersService;


    public OrdersController(ClientsService clientsService, OrdersService ordersService) {
        this.clientsService = clientsService;
        this.ordersService = ordersService;
    }

    public String getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT','MANAGER')")
    @GetMapping
    public List<OrdersEntity> findByClientId() {
        String login = getCurrentUserLogin();
        Optional<ClientsEntity> client = clientsService.findClientIdByClientLogin(login);
        return client.map(clientsEntity -> ordersService.findByClientId(clientsEntity.getClientId())).orElse(null);

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @RequestMapping(value = "/delete/{orderId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteOrder(@PathVariable Integer orderId) {
        //TODO add check for user can delete and create only their orders

        ordersService.deleteById(orderId);
        return "deleted";
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Optional<OrdersEntity> findOrder(@PathVariable Integer orderId) {
        return ordersService.findOrder(orderId);
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public void addOrder(@RequestBody OrdersEntity order) {
        ordersService.save(order);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/all")
    public List<OrdersEntity> findAll() {
        return ordersService.findAll();
    }
}
