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
    private final EmailService emailService;

    public ClientsService(ClientsRepository clientsRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.clientsRepository = clientsRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<ClientsEntity> findAll() {
        return clientsRepository.findAll();
    }

    public Optional<ClientsEntity> findById(Integer id) {
        return clientsRepository.findById(id);
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
//        System.out.println(clientLogin);
        return clientsRepository.findByClientLogin(clientLogin);
    }
    public void updateClientActivatedStatus(int clientId, Integer activated) {
        ClientsEntity client = clientsRepository.findById(clientId).get();
        client.setActivated(activated);
        clientsRepository.save(client);
    }
    public String registerNewUser(@RequestBody ClientsDTO clientsDTO) {
        if(this.findClientIdByClientLogin(clientsDTO.getClientLogin()).isEmpty()) {
            ClientsEntity client = new ClientsEntity();
            client.setClientPhone(clientsDTO.getClientPhone());
            client.setClientLogin(clientsDTO.getClientLogin());
            client.setClientFname(clientsDTO.getClientFname());
            client.setClientLname(clientsDTO.getClientLname());
            client.setClientEmail(clientsDTO.getClientEmail());
            client.setClientPassword(passwordEncoder.encode(clientsDTO.getClientPassword()));
            client.setClientIndex(clientsDTO.getClientIndex());
            client.setClientCity(clientsDTO.getClientCity());
            client.setClientAddress(clientsDTO.getClientAddress());
            client.setUserRole(clientsDTO.getUserRole());
            client.setActivated(0);
            this.save(client);
//            int exists = this.save(client);
//            if (exists == -1)

            String to = client.getClientEmail();
            String subject = "Welcome!";
            String body = "Please confirm your mail by clicking on the link\n" + "http://localhost:8080/user/activate/" + client.getClientId();
            emailService.sendEmail(to, subject, body);
            return "redirect:/login";
        }
        return "User With this login exists";
    }

    public Optional<ClientsEntity> findClientIdByClientLogin(String clientLogin) {
        return clientsRepository.findClientIdByClientLogin(clientLogin);
    }

}
