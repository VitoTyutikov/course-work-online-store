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
        clientsRepository.save(clientsEntity);
        return clientsEntity.getClientId();
    }

    public void deleteById(Integer id) {
        clientsRepository.deleteById(id);
    }


    public Optional<ClientsEntity> findByClientLogin(String clientLogin){
        System.out.println(clientLogin);
        return clientsRepository.findByClientLogin(clientLogin);
    }
    public String registerNewUser(@RequestBody ClientsDTO clientsDTO){
        ClientsEntity client = new ClientsEntity();
        if(clientsRepository.findByClientLogin(clientsDTO.getClientLogin()).isPresent())
            return "User With this login exists";
        client.setClientLogin(clientsDTO.getClientLogin());
        client.setClientFname(clientsDTO.getClientFname());
        client.setClientLname(clientsDTO.getClientLname());
        client.setClientEmail(clientsDTO.getClientEmail());
        client.setClientPassword(passwordEncoder.encode(clientsDTO.getClientPassword()));
        client.setClientIndex(clientsDTO.getClientIndex());
        client.setClientCity(clientsDTO.getClientCity());
        client.setClientAddress(clientsDTO.getClientAddress());
        client.setUserRole(clientsDTO.getUserRole());
        clientsRepository.save(client);

        return "S";//TODO change it
    }

    public  Optional<ClientsEntity> findClientIdByClientLogin(String clientLogin){
        return clientsRepository.findClientByClientLogin(clientLogin);
    }


}
