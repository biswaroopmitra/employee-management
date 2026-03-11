package com.practice.restapi.department;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "name is required.")
    @Column(nullable = false)
    String name;

    @NotNull(message = "code is required.")
    @Column(nullable = false)
    String code;

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;
}
