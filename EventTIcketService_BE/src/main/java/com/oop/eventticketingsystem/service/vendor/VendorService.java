package com.oop.eventticketingsystem.service.vendor;

import com.oop.eventticketingsystem.model.Vendor;
import com.oop.eventticketingsystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A class for VendorService
 */
@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // Get all vendors
    public List<Vendor> getVendorsLimited() {
        return vendorRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    public void saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }
}
