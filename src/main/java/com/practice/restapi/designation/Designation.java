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

    public Designation() {
    }

    public Designation(String name, String code, LocalDate fromDate, LocalDate toDate, Boolean active) {
        this.name = name;
        this.code = code;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
