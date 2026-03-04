package com.practice.restapi.cloudvendor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor {
    @Id
    String vendorId;
    String vendorName;
    String vendorAddress;
    String vendorMobileNumber;

    public Vendor() {
    }

    public Vendor(String vendorId, String vendorName, String vendorAddress, String vendorMobileNumber) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorMobileNumber = vendorMobileNumber;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorMobileNumber() {
        return vendorMobileNumber;
    }

    public void setVendorMobileNumber(String vendorMobileNumber) {
        this.vendorMobileNumber = vendorMobileNumber;
    }
}
