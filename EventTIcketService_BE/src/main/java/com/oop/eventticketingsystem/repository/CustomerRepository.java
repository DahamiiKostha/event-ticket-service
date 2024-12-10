package com.oop.eventticketingsystem.repository;

import com.oop.eventticketingsystem.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

/**
 * A class for CustomerRepository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    @NonNull
    Page<Customer> findAll(@NonNull Pageable pageable);
}
