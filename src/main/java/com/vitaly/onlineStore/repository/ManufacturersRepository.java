package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.ManufacrurersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturersRepository extends JpaRepository<ManufacrurersEntity,Integer> {

}
