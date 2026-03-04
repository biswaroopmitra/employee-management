package com.practice.restapi.cloudvendor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("vendorById/{vendorId}")
    public Vendor getVendor(@PathVariable("vendorId") long vendorId){
        return vendorService.getVendor(vendorId);
    }

    //List
    @GetMapping("allVendors")
    public List<Vendor> getAllVendors(@PathVariable int pageNumber, @PathVariable int pageSize, String sortDirection, String sortColumn){
        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortColumn).ascending();
        } else {
            sort = Sort.by(sortColumn).descending();
        }
        return vendorService.getAllVendors(PageRequest.of(pageNumber, pageSize, sort));
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
