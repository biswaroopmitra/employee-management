package com.practice.restapi.cloudvendor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("vendorById/{vendorId}")
    public Vendor getVendor(@PathVariable("vendorId") String vendorId){
        return vendorService.getVendor(vendorId);
    }

    //List
    @GetMapping("allVendors")
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
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
}
