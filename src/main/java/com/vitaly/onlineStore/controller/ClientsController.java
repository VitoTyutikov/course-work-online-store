package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.dto.ClientsDTO;
import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.service.ClientsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ClientsController {
    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    public String getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "/registration", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerNewUser(@RequestBody ClientsDTO clientsDTO) {
        return clientsService.registerNewUser(clientsDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = {RequestMethod.GET})
    public List<ClientsEntity> findAll() {
        return clientsService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT','MANAGER')")
    @GetMapping
    public Optional<ClientsEntity> getUserInfo() {
        String login = getCurrentUserLogin();
        return clientsService.findByClientLogin(login);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteById(@PathVariable Integer id) {
        clientsService.deleteById(id);
    }

}
