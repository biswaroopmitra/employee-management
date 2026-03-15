package com.practice.restapi.employee;

import com.practice.restapi.baseEntity.BaseEntity;
import com.practice.restapi.city.City;
import com.practice.restapi.department.Department;
import com.practice.restapi.designation.Designation;
import com.practice.restapi.zipcode.Zipcode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee extends BaseEntity {

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
    @Column(nullable = false)
    String employeeCode;

    @Column(nullable = true)
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
    @Positive(message = "Salary must be a positive number")
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

    @NotNull(message = "active is required.")
    @Column(nullable = false)
    Boolean active;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String employeeCode, String email, String mobileNumber, LocalDate dateOfBirth, LocalDate dateOfJoiningOrg, double salary, EmployeeStatus employeeStatus, Employee reportingManager, Designation designation, Department department, City city, Zipcode zipcode, Boolean active) {
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
        this.designation = designation;
        this.department = department;
        this.city = city;
        this.zipcode = zipcode;
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

    public Employee getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Employee reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Zipcode getZipcode() {
        return zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
