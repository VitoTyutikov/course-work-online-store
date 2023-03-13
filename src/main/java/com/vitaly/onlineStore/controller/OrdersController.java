package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.OrdersEntity;
import com.vitaly.onlineStore.service.ClientsService;
import com.vitaly.onlineStore.service.OrdersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

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
    @RequestMapping(method = {RequestMethod.GET})
    public List<OrdersEntity> findByClientId() {
        String login = getCurrentUserLogin();
        Integer clientId = clientsService.findClientIdByClientLogin(login);
        return ordersService.findByClientId(clientId);

    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<OrdersEntity> findOrder(Integer orderId){
        return ordersService.findOrder(orderId);
    }
    @RequestMapping(value = "add", method = {RequestMethod.GET, RequestMethod.POST})
    public void addOrder(@RequestBody OrdersEntity order){
        ordersService.save(order);
    }
}
