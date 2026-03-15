package com.practice.restapi.zipcode;

import com.practice.restapi.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Zipcode extends BaseEntity {
    @NotNull(message = "name is required.")
    @NotBlank(message = "name is required.")
    @Column(nullable = false)
    String name;

    @NotNull(message = "code is required.")
    @NotBlank(message = "code is required.")
    @Column(nullable = false)
    String zipcode;

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;

    public Zipcode() {
    }

    public Zipcode(String name, String zipcode, Boolean active) {
        this.name = name;
        this.zipcode = zipcode;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
