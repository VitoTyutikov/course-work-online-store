package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "clients", schema = "online_shop", catalog = "online_shop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer clientId;
    @Basic
    @Column(name = "client_fname", nullable = false)
    private String clientFname;
    @Basic
    @Column(name = "client_lname", nullable = false)
    private String clientLname;
    @Basic
    @Column(name = "client_login", nullable = false)
    private String clientLogin;
    @Basic
    @Column(name = "client_email", nullable = false)
    private String clientEmail;
    @Basic
    @Column(name = "client_password", nullable = false)
    private String clientPassword;
    @Basic
    @Column(name = "client_index")
    private Integer clientIndex;
    @Basic
    @Column(name = "client_city")
    private String clientCity;
    @Basic
    @Column(name = "client_address")
    private String clientAddress;
    @OneToMany(mappedBy = "clientsByClientId")
    private List<OrdersEntity> ordersByClientId;
}
