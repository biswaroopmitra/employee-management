package com.practice.employeemanagement.zipcode;

import com.practice.employeemanagement.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Zipcode extends BaseEntity {

    @Column(nullable = true)
    String areaName;

    @NotNull(message = "code is required.")
    @NotBlank(message = "code is required.")
    @Column(nullable = false)
    String zipcode;

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;

    public Zipcode() {
    }

    public Zipcode(String areaName, String zipcode, Boolean active) {
        this.areaName = areaName;
        this.zipcode = zipcode;
        this.active = active;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
