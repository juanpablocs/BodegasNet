package com.cibertec.bodegasNet.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true)
	private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth; // Considera usar java.util.Date con @Temporal para fechas

    @Column(name = "created_at")
    private Date createdAt; // Considera usar java.util.Date con @Temporal para fechas

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    private Role role;

    @OneToMany(mappedBy = "admin")
    private List<Merchant> merchants;
    
    // Getters and Setters
}
