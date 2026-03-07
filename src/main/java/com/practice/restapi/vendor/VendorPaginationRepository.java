package com.practice.restapi.vendor;

import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface VendorPaginationRepository extends ListPagingAndSortingRepository<Vendor, Long> {
}
