package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "deliveries", schema = "online_shop", catalog = "online_shop")
@IdClass(DeliveriesPK.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Basic
    @Column(name = "store_id", nullable = false)
    private Integer storeId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;
    @Basic
    @Column(name = "products_count", nullable = false)
    private Integer productsCount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsByProductId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private StoresEntity storesByStoreId;

    public DeliveriesPK getId() {
        DeliveriesPK deliveriesPK = new DeliveriesPK();
        deliveriesPK.setDeliveryDate(this.deliveryDate);
        deliveriesPK.setProductId(this.productId);
        return deliveriesPK;
    }
}
