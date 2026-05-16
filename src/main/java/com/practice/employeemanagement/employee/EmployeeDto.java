package com.practice.employeemanagement.employee;

import com.practice.employeemanagement.baseEntity.BaseEntity;
import com.practice.employeemanagement.city.City;
import com.practice.employeemanagement.department.Department;
import com.practice.employeemanagement.designation.Designation;
import com.practice.employeemanagement.zipcode.Zipcode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class EmployeeDto {

    @NotNull(message = "id is required.")
    long id;

    @NotNull(message = "firstName is required.")
    @NotBlank(message = "firstName is required.")
    @Column(nullable = false)
    String firstName;

    @NotNull(message = "lastName is required.")
    @NotBlank(message = "lastName is required.")
    @Column(nullable = false)
    String lastName;

    @NotNull(message = "employeeCode is required.")
    @NotBlank(message = "employeeCode is required.")
    @Column(unique = true, nullable = false)
    String employeeCode;

    @Column(unique = true, nullable = true)
    String email;

    @Column(unique = true, nullable = true)
    String mobileNumber;

    @NotNull(message = "date of birth is required.")
    @Column(nullable = false)
    LocalDate dateOfBirth;

    @NotNull(message = "date of joining org is required.")
    @Column(nullable = false)
    LocalDate dateOfJoiningOrg;

    @NotNull(message = "salary is required.")
    @Positive(message = "Salary must be a positive number")
    @Column(nullable = false)
    double salary;

    @NotNull(message = "status is required.")
    @Enumerated(EnumType.STRING)
    EmployeeStatus employeeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_manager_id", nullable = true)
    EmployeeDto reportingManager;

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, String employeeCode, String email, String mobileNumber, LocalDate dateOfBirth, LocalDate dateOfJoiningOrg, double salary, EmployeeStatus employeeStatus, EmployeeDto reportingManager, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeCode = employeeCode;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoiningOrg = dateOfJoiningOrg;
        this.salary = salary;
        this.employeeStatus = employeeStatus;
        this.reportingManager = reportingManager;
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoiningOrg() {
        return dateOfJoiningOrg;
    }

    public void setDateOfJoiningOrg(LocalDate dateOfJoiningOrg) {
        this.dateOfJoiningOrg = dateOfJoiningOrg;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public EmployeeDto getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(EmployeeDto reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
