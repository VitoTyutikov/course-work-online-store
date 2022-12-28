package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products", schema = "online_shop", catalog = "online_shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Basic
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Basic
    @Column(name = "manufacturer_id", nullable = false)
    private Integer manufacturerId;
    @Basic
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
    @Basic
    @Column(name = "product_price", nullable = false, precision = 2)
    private Double productPrice;
    @Basic
    @Column(name = "product_description", nullable = false)
    private String productDescription;
    @Basic
    @Column(name = "product_image")
    private String productImage;
    @OneToMany(mappedBy = "productsByProductId")
    private List<DeliveriesEntity> deliveriesByProductId;
    @OneToMany(mappedBy = "productsByProductId")
    private List<OrderItemsEntity> orderItemsByProductId;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", insertable = false, updatable = false)
    private ManufacrurersEntity manufacrurersByManufacturerId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private CategoriesEntity categoriesByCategoryId;
}
