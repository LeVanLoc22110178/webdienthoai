package com.leloc.vn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Brands")
@Data
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandID")
    private Long brandId;

    @Column(name = "BrandName", nullable = false)
    private String brandName;
}