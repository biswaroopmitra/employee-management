package com.practice.restapi.vendor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    VendorRepository vendorRepository;

    VendorPaginationRepository vendorPaginationRepository;

    public VendorService(VendorRepository vendorRepository, VendorPaginationRepository vendorPaginationRepository) {
        this.vendorRepository = vendorRepository;
        this.vendorPaginationRepository = vendorPaginationRepository;
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

    public List<Vendor> getAllVendors(int pageNumber, int pageSize){
        return vendorRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }
}
