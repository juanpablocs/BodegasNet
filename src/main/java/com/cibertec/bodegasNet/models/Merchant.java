package com.cibertec.bodegasNet.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "country_code")
    private Integer countryCode;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "merchant")
    private List<Product> products;

    // Getters and Setters
}