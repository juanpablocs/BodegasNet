package com.cibertec.bodegasNet.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String status;

    private Integer payed;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    
    // Getters and Setters
}
