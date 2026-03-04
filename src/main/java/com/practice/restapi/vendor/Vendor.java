package com.practice.restapi.vendor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor {
    @Id
    long vendorId;
    String vendorName;
    String vendorAddress;
    String vendorMobileNumber;

    public Vendor() {
    }

    public Vendor(long vendorId, String vendorName, String vendorAddress, String vendorMobileNumber) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorMobileNumber = vendorMobileNumber;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
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
