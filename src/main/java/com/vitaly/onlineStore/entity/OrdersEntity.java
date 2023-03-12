package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders", schema = "online_shop", catalog = "online_shop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Basic
    @Column(name = "client_id", nullable = false)
    private Integer clientId;
    @Basic
    @Column(name = "store_id", nullable = false)
    private Integer storeId;
    @Basic
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Basic
    @Column(name = "order_status", nullable = false)
    private String orderStatus;


    //    @OneToMany(mappedBy = "ordersByOrderId")
//    private List<OrderItemsEntity> orderItemsByOrderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false, insertable = false, updatable = false)
    private ClientsEntity clientsByClientId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private StoresEntity storesByStoreId;

}
