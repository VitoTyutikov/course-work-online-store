package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.StoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<StoresEntity,Integer> {

}
