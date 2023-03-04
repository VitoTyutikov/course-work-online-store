package com.vitaly.onlineStore.service;

import com.vitaly.onlineStore.entity.StoresEntity;
import com.vitaly.onlineStore.repository.StoresRepository;

import java.util.List;

public class StoresService {
    private final StoresRepository storesRepository;

    public StoresService(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    public List<StoresEntity> findAll() {
        return storesRepository.findAll();
    }

    public StoresEntity findById(Integer id) {
        return storesRepository.findById(id).orElseThrow();
    }

    public Integer save(StoresEntity storesEntity) {
        storesRepository.save(storesEntity);
        return storesEntity.getStoreId();
    }

    public void deleteById(Integer id) {
        storesRepository.deleteById(id);
    }
}
