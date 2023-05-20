package com.vitaly.onlineStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsDTO {
    private String clientFname;
    private String clientLname;
    private String clientLogin;
    private String clientEmail;
    private String clientPhone;
    private String clientPassword;
    private Integer clientIndex;
    private String clientCity;
    private String clientAddress;
    private String userRole;
}
