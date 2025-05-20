package com.leloc.vn.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private Order order;

    @Column(name = "PaymentDate", nullable = false)
    private String paymentDate;

    @Column(name = "PaymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "PaymentStatus", nullable = false)
    private String paymentStatus;

    @Column(name = "Amount", nullable = false)
    private Double amount;
}