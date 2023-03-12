package com.vitaly.onlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reviews", schema = "online_shop", catalog = "online_shop")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id", nullable = false)
    private Integer review_id;
    @Basic
    @Column(name = "product_id", nullable = false)
    private Integer product_id;
    @Basic
    @Column(name = "client_id", nullable = false)
    private Integer client_id;
    @Basic
    @Column(name = "review_date", nullable = false)
    private LocalDate review_date;
    @Basic
    @Column(name = "review_title", nullable = false)
    private String review_title;
    @Basic
    @Column(name = "review_text", nullable = false)
    private String review_text;
    @Basic
    @Column(name = "review_rating", nullable = false)
    private Double review_rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productByProductId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false, insertable = false, updatable = false)
    private ClientsEntity clientByClientId;
}
