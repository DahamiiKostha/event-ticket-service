package com.oop.eventticketingsystem.repository;

import com.oop.eventticketingsystem.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

/**
 * A class for TicketRepository
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Override
    @NonNull
    Page<Ticket> findAll(@NonNull Pageable pageable);
}
