package com.practice.restapi.employee;

import com.practice.restapi.exceptions.EmployeeNotFoundException;
import com.practice.restapi.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    //Create
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    //Update
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handlingVendorNotFound(EmployeeNotFoundException exception){
        ErrorResponse employeeNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Employee not found.");
        return new ResponseEntity<>(employeeNotFound, HttpStatus.NOT_FOUND);
    }
}
