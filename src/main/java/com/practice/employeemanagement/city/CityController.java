package com.practice.employeemanagement.city;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {

    CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("createCity")
    public City createCity(City city){
        return cityService.createCity(city);
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
