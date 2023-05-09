package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.dto.ClientsDTO;
import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.repository.ClientsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientsService(ClientsRepository clientsRepository, PasswordEncoder passwordEncoder) {
        this.clientsRepository = clientsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ClientsEntity> findAll() {
        return clientsRepository.findAll();
    }

    public ClientsEntity findById(Integer id) {
        return clientsRepository.findById(id).orElseThrow();
    }

    public Integer save(ClientsEntity clientsEntity) {
        Optional<ClientsEntity> client = findByClientLogin(clientsEntity.getClientLogin());
        if (client.isEmpty()) {

            clientsRepository.save(clientsEntity);
            return clientsEntity.getClientId();
        }
        return -1;
    }

    public void deleteById(Integer id) {
        clientsRepository.deleteById(id);
    }


    public Optional<ClientsEntity> findByClientLogin(String clientLogin) {
        System.out.println(clientLogin);
        return clientsRepository.findByClientLogin(clientLogin);
    }

    public String registerNewUser(@RequestBody ClientsDTO clientsDTO) {
        ClientsEntity client = new ClientsEntity();
        client.setClientLogin(clientsDTO.getClientLogin());
        client.setClientFname(clientsDTO.getClientFname());
        client.setClientLname(clientsDTO.getClientLname());
        client.setClientEmail(clientsDTO.getClientEmail());
        client.setClientPassword(passwordEncoder.encode(clientsDTO.getClientPassword()));
        client.setClientIndex(clientsDTO.getClientIndex());
        client.setClientCity(clientsDTO.getClientCity());
        client.setClientAddress(clientsDTO.getClientAddress());
        client.setUserRole(clientsDTO.getUserRole());
//        clientsRepository.save(client);
        int exists = this.save(client);
        if (exists == -1)
            return "User With this login exists";
        return "S";//TODO change it
    }

    public Optional<ClientsEntity> findClientIdByClientLogin(String clientLogin) {
        return clientsRepository.findClientIdByClientLogin(clientLogin);
    }


}
