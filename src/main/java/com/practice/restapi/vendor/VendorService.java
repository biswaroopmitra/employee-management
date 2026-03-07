package com.practice.restapi.vendor;

import com.practice.restapi.employee.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendorService {

    VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(Vendor vendor){
        vendorRepository.saveAndFlush(vendor);
        return vendor;
    }

    public Vendor updateVendor(Vendor vendor){
        vendorRepository.saveAndFlush(vendor);
        return vendor;
    }

    public Vendor getVendor(long vendorId){
        return vendorRepository.findById(vendorId).get();
    }

    public void deleteVendor(long vendorId){
        vendorRepository.deleteById(vendorId);
    }

    public List<Vendor> getAllVendors(int pageNumber, int pageSize){
        List<Vendor> vendors = vendorRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        List<Employee> employees = Arrays.asList(
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

        groupedEmployees.forEach((dept, employee) -> {
            System.out.print("Dept: "+ dept + "|");
            employee.ifPresentOrElse(
                    emp -> System.out.println("2nd highest: " + emp.getName() + "($" + emp.getSalary() + ")"),
                    () -> System.out.println("2nd highsest not found")
            );
        });        ;
        return vendors;
    }
}
