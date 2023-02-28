package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

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
    //    @OneToMany(mappedBy = "ordersByOrderId")
//    private List<OrderItemsEntity> orderItemsByOrderId;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false, insertable = false, updatable = false)
    private ClientsEntity clientsByClientId;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private StoresEntity storesByStoreId;

}
