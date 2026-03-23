package com.practice.employeemanagement.department;

import com.practice.employeemanagement.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department addDepartment(Department department){
        if(stringInvalid(department.name)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name.");

        if(stringInvalid(department.code)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");

        if(!Boolean.TRUE.equals(department.active) && !Boolean.FALSE.equals(department.active)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");

        return departmentRepository.saveAndFlush(department);
    }

    public ResponseEntity<?> getDepartmentById(long departmentId){
        Department department = departmentRepository.findById(departmentId)
                                            .orElseThrow(() -> new NotFoundException("City not found with ID"));

        return ResponseEntity.ok(department);
    }

    public List<Department> getAllDepartments(int pageNumber, int pageSize){
        List<Department> departments = departmentRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        return departments;
    }

    private Boolean stringInvalid(String stringValue){
        return stringValue == null || stringValue.isBlank();
    }

    public Department updateDepartment(Department department){
        Department departmentToUpdate = departmentRepository.findById(department.getId()).orElseThrow(() -> new NotFoundException("city not found with ID."));

        if(department.name != null) {
            if(department.name.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name.");
        }

        if(department.code != null) {
            if(department.code.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");
        }

        if(department.active != null) {
            if (!Boolean.TRUE.equals(department.active) && !Boolean.FALSE.equals(department.active))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");
        }

        departmentToUpdate.setName(department.getName());
        departmentToUpdate.setCode(department.getCode());
        departmentToUpdate.setActive(department.getActive());

        return departmentRepository.saveAndFlush(departmentToUpdate);
    }
}
