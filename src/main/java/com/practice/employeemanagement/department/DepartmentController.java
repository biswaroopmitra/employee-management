package com.practice.employeemanagement.department;

import com.practice.employeemanagement.city.City;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("addDepartment")
    public Department addDepartment(@RequestBody @NotNull Department department){
        return departmentService.addDepartment(department);
    }

    @GetMapping("departmentById/{departmentId}")
    @ResponseBody
    public ResponseEntity<?> getDepartment(@PathVariable("cityId") long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    //List
    @GetMapping("allDepartments")
    public List<Department> getAllDepartments(@RequestParam int pageNumber, @RequestParam int pageSize){
        return departmentService.getAllDepartments(pageNumber, pageSize);
    }

    @PutMapping("updateDepartment")
    public Department updateDepartment(Department department){
        return departmentService.updateDepartment(department);
    }
}
