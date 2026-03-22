package com.practice.employeemanagement.city;

import com.practice.employeemanagement.exceptions.NotFoundException;
import com.practice.employeemanagement.state.StateRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CityService {

    CityRepository cityRepository;
    StateRepository stateRepository;

    public CityService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public City addCity(City city){
        if(stringInvalid(city.name)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name.");

        if(stringInvalid(city.code)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");

        if(!Boolean.TRUE.equals(city.active) && !Boolean.FALSE.equals(city.active)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");

        city.state = stateRepository.findById(city.state.getId())
                                                        .orElseThrow(() -> new NotFoundException("State not found"));

        return cityRepository.saveAndFlush(city);
    }

    public ResponseEntity<?> getCityById(long cityId){
        City city = cityRepository.findById(cityId)
                                            .orElseThrow(() -> new NotFoundException("City not found with ID"));

        return ResponseEntity.ok(city);
    }

    public List<City> getAllCities(int pageNumber, int pageSize){
        List<City> cities = cityRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        return cities;
    }

    private Boolean stringInvalid(String stringValue){
        return stringValue == null || stringValue.isBlank();
    }

    public City updateCity(City city){
        City cityToUpdate = cityRepository.findById(city.getId()).orElseThrow(() -> new NotFoundException("city not found with ID."));

        if(city.name != null) {
            if(city.name.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name.");
        }

        if(city.code != null) {
            if(city.code.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");
        }

        if(city.active != null) {
            if (!Boolean.TRUE.equals(city.active) && !Boolean.FALSE.equals(city.active))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");
        }

        if(city.state != null){
            city.state = stateRepository.findById(city.state.getId())
                                                            .orElseThrow(() -> new NotFoundException("State not found"));
        }

        cityToUpdate.setState(city.getState());
        cityToUpdate.setName(city.getName());
        cityToUpdate.setCode(city.getCode());
        cityToUpdate.setActive(city.getActive());

        return cityRepository.saveAndFlush(cityToUpdate);
    }
}
