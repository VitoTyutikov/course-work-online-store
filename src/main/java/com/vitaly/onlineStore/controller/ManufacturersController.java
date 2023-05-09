package com.vitaly.onlineStore.controller;

import com.vitaly.onlineStore.entity.ManufacturersEntity;
import com.vitaly.onlineStore.service.ManufacturersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturersController {
    private final ManufacturersService manufacturersService;

    public ManufacturersController(ManufacturersService manufacturersService) {
        this.manufacturersService = manufacturersService;
    }

    @GetMapping
    public List<ManufacturersEntity> findAll() {
        return manufacturersService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void deleteById(@PathVariable Integer id) {
        manufacturersService.deleteById(id);
    }
}
