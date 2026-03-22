package com.practice.employeemanagement.zipcode;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipcodeController {

    ZipcodeService zipcodeService;

    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("addZipcode")
    public Zipcode addZipcode(@RequestBody @NotNull Zipcode zipcode){
        return zipcodeService.addZipcode(zipcode);
    }

    @GetMapping("zipcodeById/{zipcodeId}")
    @ResponseBody
    public ResponseEntity<?> getZipcode(@PathVariable("zipcodeId") long zipcodeId){
        return zipcodeService.getZipcodeById(zipcodeId);
    }

    //List
    @GetMapping("allZipcodes")
    public List<Zipcode> getAllZipcodes(@RequestParam int pageNumber, @RequestParam int pageSize){
        return zipcodeService.getAllZipcodes(pageNumber, pageSize);
    }

    @PutMapping("updateZipcode")
    public Zipcode updateZipcode(Zipcode zipcode){
        return zipcodeService.updateZipcode(zipcode);
    }
}
