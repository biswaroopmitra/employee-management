package com.practice.restapi.employee;

import com.practice.restapi.city.City;
import com.practice.restapi.city.CityRepository;
import com.practice.restapi.department.Department;
import com.practice.restapi.department.DepartmentRepository;
import com.practice.restapi.designation.Designation;
import com.practice.restapi.designation.DesignationRepository;
import com.practice.restapi.exceptions.NotFoundException;
import com.practice.restapi.zipcode.Zipcode;
import com.practice.restapi.zipcode.ZipcodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    DesignationRepository designationRepository;
    CityRepository cityRepository;
    ZipcodeRepository zipcodeRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                            DesignationRepository designationRepository,
                            CityRepository cityRepository,
                            ZipcodeRepository zipcodeRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.designationRepository = designationRepository;
        this.cityRepository = cityRepository;
        this.zipcodeRepository = zipcodeRepository;
    }

    @Transactional
    public Employee createEmployee(Employee employee){
        Department department = departmentRepository.findById(employee.department.getId())
                                                    .orElseThrow(() -> new NotFoundException("Department not found"));

        Designation designation = designationRepository.findById(employee.designation.getId())
                                    .orElseThrow(() -> new NotFoundException("Designation not found"));

        City city = cityRepository.findById(employee.city.getId())
                                    .orElseThrow(() -> new NotFoundException("City not found"));

        if(employee.zipcode.getZipcode() != null && !employee.zipcode.getZipcode().isBlank()){
            Zipcode zipcode = zipcodeRepository.findByZipcode(employee.zipcode.getZipcode())
                                                .orElseThrow(() -> new NotFoundException("Zipcode not found"));
        } else {
            Zipcode zipcode = zipcodeRepository.findById(employee.zipcode.getId())
                                .orElseThrow(() -> new NotFoundException("Zipcode not found"));
        }

        if(employee.reportingManager != null){
            employee.reportingManager = employeeRepository.findById(employee.reportingManager.getId())
                                        .orElseThrow(() -> new NotFoundException("Zipcode not found"));

        }

        Employee newEmployee = new Employee();
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setEmployeeCode(employee.getEmployeeCode());
        employee.setDateOfBirth(employee.getDateOfBirth());
        employee.setEmployeeStatus(employee.getEmployeeStatus());
        employee.setCity(employee.getCity());
        employee.setDepartment(employee.getDepartment());
        employee.setDesignation(employee.getDesignation());
        employee.setZipcode(employee.getZipcode());
        employee.setSalary(employee.getSalary());

        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    public ResponseEntity<?> getEmployeeById(long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                                        .orElseThrow(() -> new NotFoundException("Employee not found with ID"));

        return ResponseEntity.ok(employee);
    }

    public void deleteEmployee(long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getAllEmployees(int pageNumber, int pageSize){
        List<Employee> employees = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        /*List<Employee> employees = Arrays.asList(
                new Employee("Employee1", 24, "Dept1", 12000),
                new Employee("Employee2", 25, "Dept2", 15000),
                new Employee("Employee3", 29, "Dept1", 16000),
                new Employee("Employee4", 28, "Dept1", 17000),
                new Employee("Employee5", 21, "Dept2", 19000),
                new Employee("Employee6", 26, "Dept2", 14000)
                );



        groupedEmployees.forEach((dept, employee) -> {
            System.out.print("Dept: "+ dept + "|");
            employee.ifPresentOrElse(
                    emp -> System.out.println("2nd highest: " + emp.getName() + "($" + emp.getSalary() + ")"),
                    () -> System.out.println("2nd highsest not found")
            );
        });

        System.out.println("Employees with sal above 12000");
        segregatedEmployees.get(true).forEach(employee -> System.out.println(employee.getName()));

        System.out.println("Employees with sal below 12000");
        segregatedEmployees.get(false).forEach(employee -> System.out.println(employee.getName()));
        */
        return employees;
    }

    private Boolean stringInvalid(String stringValue){
        return stringValue == null || stringValue.isBlank();
    }
}
