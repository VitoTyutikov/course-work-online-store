package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Integer> {
    Optional<ClientsEntity> findByClientLogin(String clientLogin);

    Optional<ClientsEntity> findClientByClientLogin(String clientLogin);
}
