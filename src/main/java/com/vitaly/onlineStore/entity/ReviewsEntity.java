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
    private Integer reviewId;
    @Basic
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Basic
    @Column(name = "client_id", nullable = false)
    private Integer clientId;
    @Basic
    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;
    @Basic
    @Column(name = "review_title", nullable = false)
    private String reviewTitle;
    @Basic
    @Column(name = "review_text", nullable = false)
    private String reviewText;
    @Basic
    @Column(name = "review_rating", nullable = false)
    private Double reviewRating;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productByProductId;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false, insertable = false, updatable = false)
    private ClientsEntity clientByClientId;
}
