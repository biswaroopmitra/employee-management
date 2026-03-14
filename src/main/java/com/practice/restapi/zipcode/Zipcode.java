package com.practice.restapi.zipcode;

import com.practice.restapi.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class Zipcode extends BaseEntity {
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
