package com.practice.restapi.vendor;

import com.practice.restapi.exceptions.ErrorResponse;
import com.practice.restapi.exceptions.VendorNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("vendorById/{vendorId}")
    @ResponseBody
    public ResponseEntity<?> getVendor(@PathVariable("vendorId") long vendorId){
        return vendorService.getVendorById(vendorId);
    }

    //List
    @GetMapping("allVendors")
    public List<Vendor> getAllVendors(@RequestParam int pageNumber, @RequestParam int pageSize){
        return vendorService.getAllVendors(pageNumber, pageSize);
    }

    //Create
    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor){
        return vendorService.createVendor(vendor);
    }

    //Update
    @PutMapping
    public Vendor updateVendor(@RequestBody Vendor vendor){
        return vendorService.updateVendor(vendor);
    }

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<?> handlingVendorNotFound(VendorNotFoundException exception){
        ErrorResponse vendorNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Vendor not found.");
        return new ResponseEntity<>(vendorNotFound, HttpStatus.NOT_FOUND);
    }
}
