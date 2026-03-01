package com.practice.restapi.cloudvendor;

import java.util.List;
import java.util.Optional;

public class CloudVendorService {

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorService(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    public CloudVendor createCloudVendor(CloudVendor cloudVendor){
        cloudVendorRepository.saveAndFlush(cloudVendor);
        return cloudVendor;
    }

    public CloudVendor updateCloudVendor(CloudVendor cloudVendor){
        cloudVendorRepository.saveAndFlush(cloudVendor);
        return cloudVendor;
    }

    public CloudVendor getCloudVendor(String cloudVendorId){
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    public void deleteCloudVendor(String cloudVendorId){
        cloudVendorRepository.deleteById(cloudVendorId);
    }

    public List<CloudVendor> getAllCloudVendors(){
        return cloudVendorRepository.findAll();
    }
}
