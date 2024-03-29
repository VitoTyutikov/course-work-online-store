package com.vitaly.onlineStore.security;

import com.vitaly.onlineStore.entity.ClientsEntity;
import com.vitaly.onlineStore.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetalisService implements UserDetailsService {


    @Autowired
    ClientsService clientsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientsEntity> clients = clientsService.findByClientLogin(username);
        return clients.map(MyUserDetalis::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }
}
