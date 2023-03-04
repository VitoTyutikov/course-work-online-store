package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.ManufacturersEntity;
import com.vitaly.onlineStore.repository.ManufacturersRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManufacturersService {
    private final ManufacturersRepository manufacturersRepository;

    public ManufacturersService(ManufacturersRepository manufacturersRepository) {
        this.manufacturersRepository = manufacturersRepository;
    }

    public List<ManufacturersEntity> findAll() {
        return manufacturersRepository.findAll();
    }

    public ManufacturersEntity findById(Integer id) {
        return manufacturersRepository.findById(id).orElseThrow();
    }

    public Integer save(ManufacturersEntity manufacturersEntity) {
        manufacturersRepository.save(manufacturersEntity);
        return manufacturersEntity.getManufacturerId();
    }

    public void deleteById(Integer id) {
        manufacturersRepository.deleteById(id);
    }
}
