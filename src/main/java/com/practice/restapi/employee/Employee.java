package com.practice.restapi.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "name is required.")
    @Column(nullable = false)
    String name;

    @NotNull(message = "employeeCode is required.")
    @Column(nullable = false)
    String employeeCode;

    @NotNull(message = "date of birth is required.")
    @Column(nullable = false)
    LocalDate dateOfBirth;

    @NotNull(message = "date of joining is required.")
    @Column(nullable = false)
    LocalDate dateOfJoining;

    @NotNull(message = "department is required.")
    @Column(nullable = false)
    String department;

    @NotNull(message = "salary is required.")
    @Column(nullable = false)
    double salary;

    @NotNull(message = "status is required.")
    @Enumerated(EnumType.STRING)
    EmployeeStatus employeeStatus;

    public Employee() {
    }

    public Employee(long id, String name, String employeeCode, LocalDate dateOfBirth, LocalDate dateOfJoining, String department, double salary) {
        this.id = id;
        this.name = name;
        this.employeeCode = employeeCode;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.department = department;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
