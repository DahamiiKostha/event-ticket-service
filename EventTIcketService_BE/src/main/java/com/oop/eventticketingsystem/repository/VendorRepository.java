package com.oop.eventticketingsystem.repository;

import com.oop.eventticketingsystem.model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * A class for VendorRepository
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    @Override
    @NonNull
    Page<Vendor> findAll(@NonNull Pageable pageable);
}
