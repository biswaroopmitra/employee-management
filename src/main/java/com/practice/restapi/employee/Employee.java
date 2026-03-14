package com.practice.restapi.employee;

import com.practice.restapi.baseEntity.BaseEntity;
import com.practice.restapi.city.City;
import com.practice.restapi.department.Department;
import com.practice.restapi.designation.Designation;
import com.practice.restapi.zipcode.Zipcode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Employee extends BaseEntity {

    @NotNull(message = "name is required.")
    @Column(nullable = false)
    String firstName;

    @NotNull(message = "name is required.")
    @Column(nullable = false)
    String lastName;

    @NotNull(message = "employeeCode is required.")
    @Column(nullable = false)
    String employeeCode;

    @NotNull(message = "email is required.")
    @Column(nullable = false)
    String email;

    @Column(nullable = true)
    String mobileNumber;

    @NotNull(message = "date of birth is required.")
    @Column(nullable = false)
    LocalDate dateOfBirth;

    @NotNull(message = "date of joining org is required.")
    @Column(nullable = false)
    LocalDate dateOfJoiningOrg;

    @NotNull(message = "salary is required.")
    @Column(nullable = false)
    double salary;

    @NotNull(message = "status is required.")
    @Enumerated(EnumType.STRING)
    EmployeeStatus employeeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_manager_id", nullable = true)
    Employee reportingManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id", nullable = false)
    Designation designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = true)
    City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zipcode_id", nullable = true)
    Zipcode zipcode;
}
