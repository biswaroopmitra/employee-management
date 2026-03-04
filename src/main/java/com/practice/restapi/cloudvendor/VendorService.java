package com.practice.restapi.cloudvendor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(Vendor vendor){
        vendorRepository.saveAndFlush(vendor);
        return vendor;
    }

    public Vendor updateVendor(Vendor vendor){
        vendorRepository.saveAndFlush(vendor);
        return vendor;
    }

    public Vendor getVendor(String vendorId){
        return vendorRepository.findById(vendorId).get();
    }

    public void deleteVendor(String vendorId){
        vendorRepository.deleteById(vendorId);
    }

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }
}
