package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.dto.ClientsDTO;
import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.service.ClientsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ClientsController {
    private final ClientsService clientsService;


    public ClientsController(ClientsService clientsService, PasswordEncoder passwordEncoder) {
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

    @GetMapping
    public List<ClientsEntity> findAll() {
        return clientsService.findAll();
    }


    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    public Optional<ClientsEntity> getUserInfo() {
        String login = getCurrentUserLogin();
        return clientsService.findByClientLogin(login);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteById(@PathVariable Integer id) {
        clientsService.deleteById(id);
    }


}
