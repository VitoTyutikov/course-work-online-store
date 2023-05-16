package com.vitaly.onlineStore;

import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.service.ClientsService;
import com.vitaly.onlineStore.service.ReviewsService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineStoreApplication {
    private final ReviewsService reviewsService;

    public OnlineStoreApplication(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }
    private ClientsService clientsService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
////        for (int i=1;i<35;i++){
////            reviewsService.updateProductRating(i);
////        }
//        ClientsEntity client = new ClientsEntity();
//        client.setClientAddress("");
//        client.setClientEmail("admin@mail.com");
//        client.setClientLname("Adminovich");
//        client.setClientFname("Admin");
//        client.setClientCity("");
//        client.setClientPassword(passwordEncoder.encode("admin"));
//        client.setClientLogin("admin");
//        client.setUserRole("ROLE_ADMIN");
//        client.setClientPhone("111");
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
//        client.setClientPhone("222");
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
//        client.setClientPhone("333");
//        clientsService.save(client);
//
//
//    }
}
