package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products", schema = "online_shop", catalog = "online_shop")
@Getter
@Setter
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

    @Basic
    @Column(name = "product_rating")
    private Double productRating;

    @Basic
    @Column(name = "product_discount")
    private Integer productDiscount;

    @Basic
    @Column(name = "product_is_active")
    private Integer productIsActive;

    //    @OneToMany(mappedBy = "productsByProductId")
//    private List<DeliveriesEntity> deliveriesByProductId;
//    @OneToMany(mappedBy = "productsByProductId")
//    private List<OrderItemsEntity> orderItemsByProductId;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", insertable = false, updatable = false)
    private ManufacturersEntity manufacturersByManufacturerId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private CategoriesEntity categoriesByCategoryId;

    public String getManufacturerName() {
        return manufacturersByManufacturerId.getManufacturerName();
    }
}
