package com.practice.restapi.vendor;

import org.springframework.data.domain.Pageable;
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

    public Vendor getVendor(long vendorId){
        return vendorRepository.findById(vendorId).get();
    }

    public void deleteVendor(long vendorId){
        vendorRepository.deleteById(vendorId);
    }

    public List<Vendor> getAllVendors(Pageable pageable){
        return vendorRepository.findAll(pageable).getContent();
    }
}
