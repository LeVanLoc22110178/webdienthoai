package com.leloc.vn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderDetails")
@Data
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "UnitPrice", nullable = false)
    private Double unitPrice;
}