package com.practice.employeemanagement.employee;

import com.practice.employeemanagement.city.CityRepository;
import com.practice.employeemanagement.department.DepartmentRepository;
import com.practice.employeemanagement.designation.DesignationRepository;
import com.practice.employeemanagement.exceptions.NotFoundException;
import com.practice.employeemanagement.paginationfiltersort.GenericSpecification;
import com.practice.employeemanagement.paginationfiltersort.SearchCriteria;
import com.practice.employeemanagement.util.Constants;
import com.practice.employeemanagement.zipcode.ZipcodeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

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

    public Employee addEmployee(Employee employee){
        employee.department = departmentRepository.findById(employee.department.getId())
                                                    .orElseThrow(() -> new NotFoundException("Department not found"));

        employee.designation = designationRepository.findById(employee.designation.getId())
                                    .orElseThrow(() -> new NotFoundException("Designation not found"));

        employee.city = cityRepository.findById(employee.city.getId())
                                    .orElseThrow(() -> new NotFoundException("City not found"));

        if(employee.zipcode.getZipcode() != null && !employee.zipcode.getZipcode().isBlank()){
            employee.zipcode = zipcodeRepository.findByZipcode(employee.zipcode.getZipcode())
                                                .orElseThrow(() -> new NotFoundException("Zipcode not found"));
        } else {
            employee.zipcode = zipcodeRepository.findById(employee.zipcode.getId())
                                .orElseThrow(() -> new NotFoundException("Zipcode not found"));
        }

        if(employee.reportingManager != null){
            employee.reportingManager = employeeRepository.findById(employee.reportingManager.getId())
                                        .orElseThrow(() -> new NotFoundException("reportingManager not found"));
        }


        return employeeRepository.saveAndFlush(employee);
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

    @CircuitBreaker(name = "employeeService", fallbackMethod = "getGroupedEmployeesFallback")
    public Map<String, Optional<Employee>> getGroupedEmployees(Map<String, String> allParams, int pageNumber, int pageSize){
        List<Employee> employees = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        Map<String, Optional<Employee>> groupedEmployees = employees.stream().collect(Collectors.groupingBy(
                                                    employee -> employee.getDepartment().getName(),
                                                        Collectors.collectingAndThen(Collectors.toList(),
                                                                employeeList -> employeeList.stream()
                                                                                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                                                                                .skip(1)
                                                                                                .findFirst())));
        return groupedEmployees;
    }

    public Map<String, Optional<Employee>> getGroupedEmployeesFallback(Map<String, String> allParams, int pageNumber, int pageSize, Throwable throwable){
        logger.error("Circuit is open  or call failed. Error: {}", throwable.getMessage());

        GenericSpecification<Employee> spec = new GenericSpecification<>();

        allParams.forEach((key, value) -> {
            //if (!key.equals("page") && !key.equals("size") && !key.equals("sort")) {
            //}
            if(key != null && !key.isBlank()) {
                if(key.equals("id")) {
                    spec.add(new SearchCriteria(key, Constants.EQUALITY_SEARCH, value));
                }

                if(key.equals("firstName")) {
                    spec.add(new SearchCriteria(key, Constants.CASE_INSENSITIVE_SEARCH, value));
                }

                if(key.equals("lastName")) {
                    spec.add(new SearchCriteria(key, Constants.CASE_INSENSITIVE_SEARCH, value));
                }

                if(key.equals("code")) {
                    spec.add(new SearchCriteria(key, Constants.CASE_INSENSITIVE_SEARCH, value));
                }

                if(key.equals("email")) {
                    spec.add(new SearchCriteria(key, Constants.EQUALITY_SEARCH, value));
                }

                if(key.equals("mobileNumber")) {
                    spec.add(new SearchCriteria(key, Constants.EQUALITY_SEARCH, value));
                }
            }
        });

        //return productRepository.findAll(spec, pageable);
        List<Employee> employees = employeeRepository.findAll(spec, PageRequest.of(pageNumber, pageSize)).getContent();

        Map<String, Optional<Employee>> groupedEmployees = employees.stream().collect(Collectors.groupingBy(
                                                            employee -> employee.getDepartment().getName(),
                                                            Collectors.collectingAndThen(Collectors.toList(),
                                                                    employeeList -> employeeList.stream()
                                                                                                    .max(Comparator.comparingDouble(Employee::getSalary)))));
        return groupedEmployees;
    }

    private Boolean stringInvalid(String stringValue){
        return stringValue == null || stringValue.isBlank();
    }
}
