package com.vitaly.onlineStore.repository;

import com.vitaly.onlineStore.entity.ManufacturersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturersRepository extends JpaRepository<ManufacturersEntity, Integer> {
    List<ManufacturersEntity> findByManufacturerNameIgnoreCase(String manufacturerName);
}
