package com.practice.employeemanagement.employee;

import com.practice.employeemanagement.exceptions.ErrorResponse;
import com.practice.employeemanagement.exceptions.NotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employeeById/{employeeId}")
    @ResponseBody
    public ResponseEntity<?> getEmployee(@PathVariable("employeeId") long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    //List
    @GetMapping("allEmployees")
    public List<Employee> getAllEmployees(@RequestParam int pageNumber, @RequestParam int pageSize){
        return employeeService.getAllEmployees(pageNumber, pageSize);
    }

    @GetMapping("secondHighestPaidEmployeeByDepartments")
    public Map<String, Optional<Employee>> getGroupedEmployees(@RequestParam int pageNumber, @RequestParam int pageSize){
        return employeeService.getGroupedEmployees(pageNumber, pageSize);
    }

    //Create
    @PostMapping("addEmployee")
    public Employee createEmployee(@RequestBody @NotNull Employee employee){

        return employeeService.createEmployee(employee);
    }

    //Update
    @PutMapping("updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlingVendorNotFound(NotFoundException exception){
        ErrorResponse employeeNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Employee not found.");
        return new ResponseEntity<>(employeeNotFound, HttpStatus.NOT_FOUND);
    }
}
