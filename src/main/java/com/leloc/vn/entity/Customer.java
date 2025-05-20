package com.leloc.vn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Long customerId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private String createdAt;

    // Thêm cột password
    @Column(name = "Password", nullable = false)
    private String password;

    // Thêm cột role (ADMIN, USER)
    @Enumerated(EnumType.STRING)
    @Column(name = "Role", nullable = false)
    private Role role;

    // Enum cho Role
    public enum Role {
        ADMIN,
        USER
    }
}
