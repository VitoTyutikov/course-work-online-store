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
    @Autowired
    private ClientsService clientsService;
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

    //TODO переписать все
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
//        clientsService.save(client);
//    }
}
