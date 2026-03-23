package com.practice.employeemanagement.city;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select city from City city left join fetch city.state where city.id=:cityId")
    Optional<City> findByIdWithState(@Param("cityId") Long cityId);
}
