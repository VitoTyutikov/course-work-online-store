package com.vitaly.onlineStore;

import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.service.ClientsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineStoreApplication {
//    @Autowired
//    private ClientsService clientsService;
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        ClientsEntity client = new ClientsEntity();
//        client.setClientAddress("");
//        client.setClientEmail("admin@mail.com");
//        client.setClientLname("Adminovich");
//        client.setClientFname("Admin");
//        client.setClientCity("");
//        client.setClientPassword(passwordEncoder.encode("admin"));
//        client.setClientLogin("admin");
//        client.setUserRole("ROLE_ADMIN");
//        client.setPhoneNumber("111");
//        clientsService.save(client);
//
//        client = new ClientsEntity();
//        client.setClientAddress("");
//        client.setClientEmail("manager@mail.com");
//        client.setClientLname("man");
//        client.setClientFname("Manager");
//        client.setClientCity("Kazan");
//        client.setClientPassword(passwordEncoder.encode("manag"));
//        client.setClientLogin("Manager");
//        client.setUserRole("ROLE_MANAGER");
//        client.setPhoneNumber("222");
//        clientsService.save(client);
//
//        client = new ClientsEntity();
//        client.setClientAddress("");
//        client.setClientEmail("client@mail.com");
//        client.setClientLname("clientovich");
//        client.setClientFname("Client");
//        client.setClientCity("Client");
//        client.setClientPassword(passwordEncoder.encode("client"));
//        client.setClientLogin("client");
//        client.setUserRole("ROLE_CLIENT");
//        client.setPhoneNumber("333");
//        clientsService.save(client);
//
//
//    }
}
