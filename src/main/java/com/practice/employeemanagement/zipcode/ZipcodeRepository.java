package com.practice.employeemanagement.zipcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {

    Optional<Zipcode> findByZipcode(String zipcode);
}
