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

    public City() {
    }

    public City(String name, String code, State state, Boolean active) {
        this.name = name;
        this.code = code;
        this.state = state;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
