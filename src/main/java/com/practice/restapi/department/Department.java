package com.practice.restapi.department;

import com.practice.restapi.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Department extends BaseEntity {

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
