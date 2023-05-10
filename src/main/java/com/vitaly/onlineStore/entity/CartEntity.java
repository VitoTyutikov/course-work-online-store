package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cart", schema = "online_shop", catalog = "online_shop")
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    @Basic
    @Column(name = "product_id")
    private Integer productId;

    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    @Basic
    @Column(name = "count_products")
    private Integer countProducts;

    @ManyToOne
    @JoinColumn(name = "client_id",referencedColumnName = "client_id", insertable = false,updatable = false,nullable = false)
    private ClientsEntity clientByClientId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productByProductId;
}
