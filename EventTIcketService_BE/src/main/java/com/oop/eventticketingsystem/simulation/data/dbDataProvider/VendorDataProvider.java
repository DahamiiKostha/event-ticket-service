package com.oop.eventticketingsystem.simulation.data.dbDataProvider;

import com.oop.eventticketingsystem.model.Vendor;
import com.oop.eventticketingsystem.service.vendor.VendorService;
import com.oop.eventticketingsystem.simulation.data.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for VendorDataProvider from Db
 */
@Component
public class VendorDataProvider implements DataProvider<Vendor> {
    private final VendorService vendorService;
    private List<Vendor> vendors = new ArrayList<>();
    private final DataStore dataStore = DataStore.getInstance();

    @Autowired
    public VendorDataProvider(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Override
    public List<Vendor> populate() {
        if (vendors.isEmpty()) {
            // Retrieve existing vendors from the data store
            List<Vendor> existingVendors = dataStore.getVendors();
            if (!existingVendors.isEmpty()) {
                // Use the existing vendors if they are already cached
                vendors = new ArrayList<>(existingVendors);
                return vendors;
            }

            // Fetch vendors from the database
            vendors = new ArrayList<>(vendorService.getVendorsLimited());
            if (vendors.isEmpty()) {
                throw new IllegalStateException("No vendors found in the database. Please ensure the database is populated.");
            }

            // Cache the retrieved vendors in the data store
            dataStore.setVendors(vendors);
        }
        return vendors;
    }
}
