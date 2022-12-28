package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "categories", schema = "online_shop", catalog = "online_shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
    @Basic
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    @OneToMany(mappedBy = "categoriesByCategoryId")
    private List<ProductsEntity> productsByCategoryId;
}
