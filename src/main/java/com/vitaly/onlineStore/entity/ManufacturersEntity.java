package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manufacturers", schema = "online_shop", catalog = "online_shop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manufacturer_id", nullable = false)
    private Integer manufacturerId;
    @Basic
    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;
//    @OneToMany(mappedBy = "manufacturersByManufacturerId")
//    private List<ProductsEntity> productsByManufacturerId;

}
