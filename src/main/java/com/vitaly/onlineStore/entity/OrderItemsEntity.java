package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items", schema = "online_shop", catalog = "online_shop")
@IdClass(OrderItemsPK.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Basic
    @Column(name = "product_count", nullable = false)
    private Integer productCount;
    @Basic
    @Column(name = "product_price", nullable = false, precision = 2)
    private Double productPrice;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    private OrdersEntity ordersByOrderId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsByProductId;

    public OrderItemsPK getId() {
        return new OrderItemsPK(this.orderId, this.productId);
    }
}
