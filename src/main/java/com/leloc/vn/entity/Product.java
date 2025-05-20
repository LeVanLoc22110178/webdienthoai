package com.leloc.vn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long productId;

    @Column(name = "ProductName", nullable = false)
    private String productName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "StockQuantity", nullable = false)
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "BrandID", referencedColumnName = "BrandID")
    private Brand brand;

    @Column(name = "ReleaseDate")
    private String releaseDate;

    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "Status", nullable = false)
    private String status;
}
