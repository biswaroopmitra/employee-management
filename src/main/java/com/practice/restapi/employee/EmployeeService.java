package com.practice.restapi.employee;

import com.practice.restapi.exceptions.EmployeeNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    public ResponseEntity<?> getEmployeeById(long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                                        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID"));

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

        Map<String, Optional<Employee>> groupedEmployees = employees.stream()
                                                    .collect(Collectors.groupingBy(Employee::getDepartment,
                                                                                    Collectors.collectingAndThen(
                                                                                               Collectors.toList(),
                                                                                            newList -> newList.stream()
                                                                                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                                                                                    .skip(1)
                                                                                                    .findFirst()
                                                                                    )));

        Map<Boolean, List<Employee>> segregatedEmployees = employees.stream()
                                                            .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 12000));

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
}
