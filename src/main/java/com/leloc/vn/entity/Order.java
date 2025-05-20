package com.leloc.vn.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    private Customer customer;

    @Column(name = "OrderDate", nullable = false)
    private Date orderDate;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "TotalAmount", nullable = false)
    private Double totalAmount;
}