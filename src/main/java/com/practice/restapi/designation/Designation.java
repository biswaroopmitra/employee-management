package com.practice.restapi.designation;

import com.practice.restapi.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Designation extends BaseEntity {

    @NotNull(message = "name is required.")
    @Column(nullable = false)
    String name;

    @NotNull(message = "code is required.")
    @Column(nullable = false)
    String code;

    @NotNull(message = "from date is required.")
    @Column(nullable = false)
    LocalDate fromDate;

    @Column(nullable = true)
    LocalDate toDate;

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;
}
