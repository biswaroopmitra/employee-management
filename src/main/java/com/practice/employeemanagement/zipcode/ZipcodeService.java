package com.practice.employeemanagement.zipcode;

import com.practice.employeemanagement.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ZipcodeService {

    ZipcodeRepository zipcodeRepository;

    public ZipcodeService(ZipcodeRepository zipcodeRepository) {
        this.zipcodeRepository = zipcodeRepository;
    }

    public Zipcode createZipcode(Zipcode zipcode){
        if(stringInvalid(zipcode.areaName)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid areaName.");

        if(stringInvalid(zipcode.zipcode)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");

        if(!Boolean.TRUE.equals(zipcode.active) && !Boolean.FALSE.equals(zipcode.active)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");

        return zipcodeRepository.saveAndFlush(zipcode);
    }

    public ResponseEntity<?> getZipcodeById(long zipcodeId){
        Zipcode zipcode = zipcodeRepository.findById(zipcodeId)
                                    .orElseThrow(() -> new NotFoundException("Zipcode not found with ID"));

        return ResponseEntity.ok(zipcode);
    }

    public List<Zipcode> getAllZipcodes(int pageNumber, int pageSize){
        List<Zipcode> zipcodes = zipcodeRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        return zipcodes;
    }

    private Boolean stringInvalid(String stringValue){
        return stringValue == null || stringValue.isBlank();
    }

    public Zipcode updateZipcode(Zipcode zipcode){
        Zipcode zipcodeToUpdate = zipcodeRepository.findById(zipcode.getId()).orElseThrow(() -> new NotFoundException("Zipcode not found with ID."));

        if(zipcode.areaName != null) {
            if(zipcode.areaName.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid areaName.");
        }

        if(zipcode.zipcode != null) {
            if(zipcode.zipcode.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");
        }

        if(zipcode.active != null) {
            if (!Boolean.TRUE.equals(zipcode.active) && !Boolean.FALSE.equals(zipcode.active))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");
        }

        zipcodeToUpdate.setAreaName(zipcode.getAreaName());
        zipcodeToUpdate.setZipcode(zipcode.getZipcode());
        zipcodeToUpdate.setActive(zipcode.getActive());

        return zipcodeRepository.saveAndFlush(zipcodeToUpdate);
    }
}
