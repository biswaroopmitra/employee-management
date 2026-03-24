package com.practice.employeemanagement.employee;

import com.practice.employeemanagement.paginationfiltersort.GenericSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    //@Query("SELECT employee FROM Employee employee LEFT JOIN FETCH employee.designation LEFT JOIN FETCH employee.department LEFT JOIN FETCH employee.city.state")

    @Query("SELECT employee.id FROM Employee employee")
    Page<Long> findEmployeeIds(Pageable pageable);

    @Query("SELECT employee.id FROM Employee employee")
    Page<Long> findEmployeeIdsPaginated(GenericSpecification<Employee> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"department", "designation", "city", "city.state", "zipcode"})
    List<Employee> findByIdIn(List<Long> ids);
}
