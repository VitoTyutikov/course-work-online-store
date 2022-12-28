package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "manufacrurers", schema = "online_shop", catalog = "online_shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacrurersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manufacturer_id", nullable = false)
    private Integer manufacturerId;
    @Basic
    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;
    @OneToMany(mappedBy = "manufacrurersByManufacturerId")
    private List<ProductsEntity> productsByManufacturerId;

}
