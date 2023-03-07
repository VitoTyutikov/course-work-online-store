package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.dto.ClientsDTO;
import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.repository.ClientsRepository;
import com.vitaly.onlineStore.service.ClientsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ClientsController {
    private final ClientsService clientsService;


    public ClientsController(ClientsService clientsService, PasswordEncoder passwordEncoder) {
        this.clientsService = clientsService;
    }

    @RequestMapping(value = "/registration", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerNewUser(@RequestBody ClientsDTO clientsDTO){
        return clientsService.registerNewUser(clientsDTO);
    }

    @GetMapping
    public List<ClientsEntity> findAll(){
        return clientsService.findAll();
    }

}
