package com.practice.restapi.city;

import com.practice.restapi.baseEntity.BaseEntity;
import com.practice.restapi.designation.Designation;
import com.practice.restapi.state.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class City extends BaseEntity {
    @NotNull(message = "name is required.")
    @Column(nullable = false)
    String name;

    @NotNull(message = "code is required.")
    @Column(nullable = false)
    String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    State state;

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;
}
