package com.practice.employeemanagement.zipcode;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/zipcode")
public class ZipcodeController {

    ZipcodeService zipcodeService;

    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("createZipcode")
    public Zipcode createZipcode(Zipcode zipcode){
        return zipcodeService.createZipcode(zipcode);
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
