package com.practice.employeemanagement.city;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("addCity")
    public City addCity(@RequestBody @NotNull City city){
        return cityService.addCity(city);
    }

    @GetMapping("cityById/{cityId}")
    @ResponseBody
    public ResponseEntity<?> getCity(@PathVariable("cityId") long cityId){
        return cityService.getCityById(cityId);
    }

    //List
    @GetMapping("allCities")
    public List<City> getAllCities(@RequestParam int pageNumber, @RequestParam int pageSize){
        return cityService.getAllCities(pageNumber, pageSize);
    }

    @PutMapping("updateCity")
    public City updateCity(City city){
        return cityService.updateCity(city);
    }
}
