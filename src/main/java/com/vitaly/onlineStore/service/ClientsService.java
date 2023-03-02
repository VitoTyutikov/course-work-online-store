package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
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
}
