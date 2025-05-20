package com.leloc.vn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reviews")
@Data
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    private Customer customer;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "ReviewDate", nullable = false)
    private String reviewDate;
}