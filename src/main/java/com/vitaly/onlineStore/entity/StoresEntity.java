package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "stores", schema = "online_shop", catalog = "online_shop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    private Integer storeId;
    @Basic
    @Column(name = "store_name", nullable = false, length = 60)
    private String storeName;
//    @OneToMany(mappedBy = "storesByStoreId")
//    private List<DeliveriesEntity> deliveriesByStoreId;
//    @OneToMany(mappedBy = "storesByStoreId")
//    private List<OrdersEntity> ordersByStoreId;
}
