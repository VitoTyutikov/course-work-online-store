package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories", schema = "online_shop", catalog = "online_shop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
    @Basic
    @Column(name = "category_name", nullable = false)
    private String categoryName;

//    @OneToMany(mappedBy = "categoriesByCategoryId")
//    private List<ProductsEntity> productsByCategoryId;
}
